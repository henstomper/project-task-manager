<script setup lang="ts">
import Users from './components/Users.vue'
import { RouterView } from 'vue-router';

</script>

<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>

table {
  width: 100%;
}

.button {
  margin: auto
}

.home {
  margin-top: 30px !important;
  margin-left: 50px !important
}

.pagetop {
  display: flex
}

.toptext {
  padding-top: 20px;
  text-align: center;
  margin: auto
}

.wrapper {
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 5%;
}

a {
  color: inherit !important;
  text-decoration: inherit !important;
}

.bg-purple {
  background-color: rgb(102, 14, 124) !important;
  color: white !important
}

table {
  border:2px solid black;
  border-collapse: collapse;
}
.header, td {
  border: 0px !important;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}
.header {
  background-color: darkgray;

}
tr:nth-child(even) {
  background-color: rgb(223, 223, 223);
}


</style>
