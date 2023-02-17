<script setup>
import HelloWorld from './components/HelloWorld.vue'</script>

<template>
  <header>
    <a href="books.html"><img alt="Vue logo" class="logo" height="125" src="./assets/logo.svg" width="125"/></a>

    <div class="wrapper">
      <!--      <HelloWorld msg="Crear libro!" />-->
    </div>
  </header>

  <main>
    <HelloWorld msg="Create Book"/>
    <!--    <TheWelcome />-->

    <label for="fname">Book name: </label>
    <input id="fname" value="john" name="fname" type="text"><br><br>
    <label for="lname">Book Author: </label>
    <input id="lname" value="smith" name="lauthor" type="text"><br><br>
    <label for="lid">Book Id: </label>
    <input id="lid"  name="lid" type="text"><br><br>
    <!--      <input type="submit" value="Submit">-->
    <button v-on:click="doStuff()">Submit</button>

  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>

<script>
export default {
  methods: {
    doStuff() {
      let fname = document.getElementById('fname').value;
      let lauthor = document.getElementById('lname').value;
      let lid = document.getElementById('lid').value;

      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          fname: fname,
          lauthor: lauthor,
          lid: lid
        })
      };
      console.log("before fetch")
      console.log(requestOptions)
      fetch("http://localhost:8084/create-book-entry", requestOptions)
          .then(response => { //https://jasonwatmore.com/post/2020/04/30/vue-fetch-http-post-request-examples
            console.log("response")
            alert("Success")
          })
          .catch(error => {
            console.error("There was an EError!");
            alert("there was an error processing the request")
          });
    }
  }
}
</script>