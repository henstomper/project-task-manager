<template>
    <b-card bg-variant="purple" text-variant="light" class="pagetop">
        <h1 class="toptext">Project Management</h1>
    </b-card>
    <div class="wrapper">
        <h2> {{ name }}</h2>
        <h3>Estimated {{ time }} Hours</h3>
      <b-table-simple hover>
        <b-thead>
            <b-tr>
                <b-th class="header" variant="secondary">Task</b-th>
                <b-th class="header" variant="secondary">Assigned To</b-th>
                <b-th class="header" variant="secondary">Estimated Hours</b-th>
            </b-tr>
        </b-thead>
        <b-tbody>
            <b-tr v-for="t in tasks">
                <b-td>{{ t.name }}</b-td>
                <b-td>{{ t.employee }}</b-td>
                <b-td>{{ t.time }}</b-td>
            </b-tr>
        </b-tbody>
      </b-table-simple>
      <b-button class="home"><router-link to="/">Home</router-link></b-button>
        <b-button class="home" variant="secondary" @click="$router.go(-1)">Back</b-button>
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
        //Once component has been mounted, make an HTTP request to fetch name, estimated time, and tasks for the specified project.
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