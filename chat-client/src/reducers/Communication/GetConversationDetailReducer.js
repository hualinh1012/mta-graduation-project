import { GET_CONVERSATION_DETAIL, CLEAR_STORE } from '../../types';

const get_conversation_detail_reducer = (state = {}, action) => {
    switch (action.type) {
        case GET_CONVERSATION_DETAIL:
            return {
                ...state,
                data: action.payload
            };
        case CLEAR_STORE:
            return {
                ...state,
                data: action.payload
            };
        default:
            return state;
    }
}

export {
    get_conversation_detail_reducer
}