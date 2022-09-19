<template>
    <div class="wrapper">
        <router-link to="/">Home</router-link>
        <h1> {{ name }}</h1>
        <h2>Estimated {{ time }} Hours</h2>
      <table>
        <tr>
            <th>Task</th>
            <th>Assigned To</th>
            <th>Estimated Hours</th>
        </tr>
        <tr v-for="t in tasks">
            <td>{{ t.name }}</td>
            <td>{{ t.employee }}</td>
            <td>{{ t.time }}</td>
        </tr>
      </table>
      <button @click="$router.go(-1)">Back</button>
    </div>
  </template>
  
  <script lang="ts">
  
    import axios from 'axios'
    import { useRoute } from 'vue-router'
  
    export default {
        data() {
            return {
                tasks: [],
                name: "",
                time: 0,
            }
        },
        mounted() {
            const id = this.$route.params.id
            console.log(id)
            console.log("Mounted")
            axios.get(`http://localhost:8080/projects/${id}`)
            .then((response) => {
                console.log(response)
                this.tasks = response.data.tasks
                this.name = response.data.name
                this.time = response.data.time
            })
        }
    }
  </script>
  
  <style scoped>
  </style>