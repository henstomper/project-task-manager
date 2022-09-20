<template>
    <b-card bg-variant="primary" text-variant="light" class="pagetop">
        <h1 class="toptext">Project Management</h1>
    </b-card>
    <div class="wrapper">
        <h2> {{ name }}'s Project List</h2>
      <b-table-simple>
        <b-thead>
            <b-tr>
                <b-th class="header" variant="secondary">Project</b-th>
                <b-th class="header" variant="secondary">Members</b-th>
                <b-th class="header" variant="secondary">Estimated Hours</b-th>
                <b-th class="header" variant="secondary">Actions</b-th>
            </b-tr>
        </b-thead>
        <b-tbody>
            <b-tr v-for="p in projects">
                <b-td>{{ p.name }}</b-td>
                <b-td><span v-for="m in p.members">{{ m }}, </span></b-td>
                <b-td>{{ p.total }} Hours</b-td>
                <b-td><b-button pill variant="primary"><router-link :to="p.url">View</router-link></b-button></b-td>
            </b-tr>
        </b-tbody>
      </b-table-simple>
        <div style="width: 100%">
            
        </div>
        <b-button class="home"><router-link to="/">Home</router-link></b-button>
        <b-button class="home" variant="secondary" @click="$router.go(-1)">Back</b-button>
    </div>
    
  </template>
  
  <script lang="ts">
  
    import axios from 'axios'
    import { reactive, ref } from 'vue'
    import { useRoute } from 'vue-router'
  
    export default {
        data() {
          return {
              projects: [],
              name: ""
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