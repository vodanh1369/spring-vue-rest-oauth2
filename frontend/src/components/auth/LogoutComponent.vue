<template>
  <a class="btn btn-block" @click.prevent="logout"></a>
</template>

<script>
  export default {
    name: "Logout",
    created() {
      let currentUser = localStorage.getItem("currentUser");
      if (currentUser && currentUser !== {}) {
        this.$store.dispatch("logout");
      }
    },
    methods: {
      logout() {
        this.$store.dispatch("fetchUser");
       
      }
    }
  };
</script>
