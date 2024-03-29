/**
|--------------------------------------------------
| ALL REDUCERS
|--------------------------------------------------
*/
import { combineReducers } from 'redux';

import {connect_socket_reducer} from './ConnectSocketReducer';
import {connect_socket_status_reducer} from './ConnectSocketStatusReducer';
import {user_login_reducer} from './Account/LoginReducer';
import {user_register_reducer} from './Account/RegisterReducer';
import {user_info_reducer} from './Account/UserInfoReducer';
import {change_password_reducer} from './Account/ChangePassReducer';
import {list_contact_reducer} from './Communication/ListContactReducer';
import {friend_info_reducer} from './Account/FriendInfoReducer';
import {start_conversation_reducer} from './Communication/StartConversationReducer';
import {load_conversation_reducer} from './Communication/LoadConversationReducer';
import {get_conversation_detail_reducer} from './Communication/GetConversationDetailReducer';
import {send_message_reducer} from './Communication/SendMessageReducer';
import {list_conversation_reducer} from './Communication/ListConversationReducer';
import {get_chat_history_reducer} from './Communication/GetChatHistoryReducer';
import {show_message_reducer} from './Communication/ShowMessageReducer';
import {load_more_chat_history_reducer} from './Communication/LoadMoreHistoryReducer';
import {get_list_available_contact_reducer} from './Communication/GetListAvailableContactReducer';
import {create_conversation_reducer} from './Communication/CreateConversationReducer';
import {get_group_conversation_detail_reducer} from './Communication/GetGroupConversationDetailReducer';
import {start_video_call_reducer} from './Call/StartVideoCallReducer';
import {start_call_reducer} from './Call/StartCallReducer';
import {receive_call_signal_reducer} from './Call/ReceiveCallSignalReducer';
import {change_group_name_reducer} from './Communication/ChangeGroupConversationNameReducer';

const reducer = combineReducers({
    connect_socket_reducer,
    connect_socket_status_reducer,
    user_login_reducer,
    user_register_reducer,
    user_info_reducer,
    change_password_reducer,
    list_contact_reducer,
    friend_info_reducer,
    start_conversation_reducer,
    load_conversation_reducer,
    get_conversation_detail_reducer,
    send_message_reducer,
    list_conversation_reducer,
    get_chat_history_reducer,
    show_message_reducer,
    load_more_chat_history_reducer,
    get_list_available_contact_reducer,
    create_conversation_reducer,
    get_group_conversation_detail_reducer,
    start_video_call_reducer,
    start_call_reducer,
    receive_call_signal_reducer,
    change_group_name_reducer
});

export default reducer;