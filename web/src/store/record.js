export default {
    state: {
        is_record: false,
        a_steps: "",
        b_steps: "",
        a_username: "",
        b_username: "",
        record_loser: "",
    },
    getters: {
    },
    mutations: {
        updateIsRecord(state, is_record) {
            state.is_record = is_record;
        },
        updateSteps(state, data) {
            state.a_steps = data.a_steps;
            state.b_steps = data.b_steps;
        },
        updateUsername(state, data) {
            state.a_username = data.a_username;
            state.b_username = data.b_username;
        },
        updateRecordLoser(state, loser) {
            state.record_loser = loser;
        }
    },
    actions: {
    },
    modules: {
    }
  }
  