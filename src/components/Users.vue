<template>
  <b-card bg-variant="primary" text-variant="light" class="pagetop">
        <h1 class="toptext">Project Management</h1>
  </b-card>
  <div class="wrapper">
    <b-table-simple hover>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th class="header" variant="secondary">Employee</b-th>
          <b-th class="header" variant="secondary">Actions</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="u in users">
          <b-td>{{ u.name }}</b-td>
          <b-td><b-button pill variant="primary"><router-link :to="u.url">View</router-link></b-button></b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    
  </div>
</template>

<script lang="ts">

  import axios from 'axios'

  export default {
      data() {
          return {
              users: []
          }
      },
      //Once component has been mounted, make an HTTP request to fetch all users
      mounted() {
          console.log("Mounted")
          axios.get("http://localhost:8080/employees")
          .then((response) => {
              console.log(response)
              this.users = response.data
              this.users.forEach((u) => {
                u.url = "/employees/" + u.id
              });
              console.log(this.users)
          })
      }
  }
</script>

<style scoped>
</style>
