<template>
    <div class="wrapper">
        <router-link to="/">Home</router-link>
        <h1> {{ name }}'s Project List</h1>
      <table>
        <tr>
            <th>Project</th>
            <th>Members</th>
            <th>Estimated Hours</th>
            <th>Actions</th>
        </tr>
        <tr v-for="p in projects">
            <td>{{ p.name }}</td>
            <td><span v-for="m in p.members">{{ m }}, </span></td>
            <td>{{ p.total }} Hours</td>
            <td><router-link :to="p.url">View</router-link></td>
        </tr>
      </table>
      <button @click="$router.go(-1)">Back</button>
    </div>
  </template>
  
  <script lang="ts">
  
    import axios from 'axios'
    import { reactive, ref } from 'vue'
    import { useRoute } from 'vue-router'
  
    export default {
        setup() {
            const projects = reactive
            const name = ref("")
            return {
                name
            }
        },
        //Once component has been mounted, make an HTTP request to fetch name and projects for specified user
        mounted() {
            const id = this.$route.params.id as BigInteger
            axios.get(`http://localhost:8080/users/${id}`)
            .then((response) => {
                this.projects = response.data.projects
                this.name = response.data.name
            })
        }
    }
  </script>
  
  <style scoped>
  </style>