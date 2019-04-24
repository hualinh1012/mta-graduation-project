import React, { Component } from 'react';
import { connect } from 'react-redux';
import { get_list_available_contact_action, add_friend_to_conversation_action} from '../../actions';

class AddFriendToConversationPopup extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list_contact: [],
            list_checked: []
        };
    }

    componentWillMount() {
        this.props.get_list_available_contact_action(this.props.conversation_id);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.list_available.data) {
            switch (nextProps.list_available.data.code) {
                case 0:
                    this.setState({
                        list_contact: nextProps.list_available.data.data
                    })
                    break;
                default:
                    break;
            }
        }
        if (nextProps.create_conversation.data) {
            console.log(nextProps.create_conversation.data)
            switch (nextProps.create_conversation.data.code) {
                case 0:
                    const { conversation_id } = nextProps.create_conversation.data.data;
                    this.props.get_conversation_detail_action(conversation_id);
                    this.props.close();
                    break;
                default:
                    break;
            }
        }
    }

    choose_contact = (e, friend_id) => {
        let { list_checked } = this.state;
        if (e.target.checked) {
            list_checked.push(friend_id);
            this.setState({
                list_checked
            })
        }
        else {
            for (let x in list_checked) {
                let id = list_checked[x];
                if (friend_id === id) {
                    list_checked.splice(x, 1);
                    break;
                }
            }
            this.setState({
                list_checked
            })
        }
    }

    add_friend_to_conversation = () => {
        const {list_checked} = this.state
        this.props.add_friend_to_conversation_action(this.props.conversation_id, list_checked);
        this.props.close();
    }

    render() {
        const { list_contact } = this.state;
        return (
            <div className='popup'>
                <div className='popup_create_conversation'>
                    <div className="popup_header">
                        <h1>Thêm liên lạc vào nhóm</h1>
                        <label className="close"><i className="fa fa-times" aria-hidden="true" onClick={this.props.close}></i></label>
                    </div>
                    <div className="popup_body">
                        <h2>Danh sách liên lạc</h2>
                        <input type="text" className="find_contact" placeholder="Tìm kiếm..."></input>
                        <ul className="list_available_contact">
                            {list_contact.map((item) => {
                                return (
                                    <li className="available_contact add_to_group" key={item.friend_id}>
                                        <img src={item.avatar_url ? item.avatar_url : '/default_ava.png'} alt="" className="online" />
                                        <p className="name" key={item.friend_id}>{item.friend_name}</p>
                                        <input type="checkbox" className="choose_contact" onChange={(e) => this.choose_contact(e, item.friend_id)}></input>
                                    </li>
                                )
                            })}
                        </ul>
                        <button onClick={(e) => this.add_friend_to_conversation()}>Thêm</button>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        list_available: state.get_list_available_contact_reducer,
        create_conversation: state.create_conversation_reducer
    }
}

export default connect(mapStateToProps, { get_list_available_contact_action, add_friend_to_conversation_action })(AddFriendToConversationPopup);