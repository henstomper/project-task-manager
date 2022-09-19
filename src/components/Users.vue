<template>
  <div class="wrapper">
    <table>
      <tr>
        <th>Employee</th>
        <th>Actions</th>
      </tr>
      <tr v-for="u in users">
        <td>{{ u.name }}</td>
        <td><router-link :to="u.url">View</router-link></td>
      </tr>
    </table>
    <button @click="$router.go(-1)">Back</button>
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
