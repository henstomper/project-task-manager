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

button {
  margin-left: 25px;
  margin-top: 25px;
  min-height: 40px;
  min-width: 60px;
}

table {
  border:1px solid black;
  border-collapse: collapse;
}
th, td {
  margin-left: auto;
  margin-right: auto;
  text-align: center;
  border-right: 1px solid black;
}
th {
  background-color: gray;
}
tr:nth-child(even) {
  background-color: lightgray;
}


</style>
