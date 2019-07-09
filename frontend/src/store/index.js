import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SET_USER = "SET_USER";

export default new Vuex.Store({
  state: {
    //currentUser: JSON.parse(localStorage.getItem("currentUser")) || {},
    currentUser: localStorage.getItem("currentUser") || {},
  },
  mutations: {
    [SET_USER](state, payload) {
      //console.log(state);
      //console.log(isEmpty(payload));
      state.currentUser = payload;

    }
  },
  actions: {
    fetchUser({commit}) {
      axios.get("http://localhost:3000/sessions/token", {withCredentials: true}).then(response => {
        commit(SET_USER, response.data);
        //localStorage.setItem("currentUser", JSON.stringify(this.state.currentUser));
        //axios.defaults.headers.common['Authorization'] = response.data;
        localStorage.setItem("currentUser", this.state.currentUser);
      }).catch(() => {
        commit(SET_USER, {});
      });
    },
    logout({commit}) {
      commit(SET_USER, {});
      localStorage.removeItem("currentUser");
      document.cookie = "backend-session" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
      //delete axios.defaults.headers.common['Authorization'];

    }
  },
  getters: {
    isAuthenticated: (state) => {
      console.log(state.currentUser);

      return Object.keys(state.currentUser).length;
      //return state.currentUser.toString().includes("name");
    }
  }
})