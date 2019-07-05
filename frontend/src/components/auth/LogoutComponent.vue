<template>
  <a class="btn btn-block" @click.prevent="logout">Logout</a>
</template>

<script>
  export default {
    name: "Logout",
    methods: {
      logout() {
      	localStorage.removeItem("currentUser");
        this.$store.dispatch("logout");
        this.$router.push('Login');
      }
    }
  };
</script>
