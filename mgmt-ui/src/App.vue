<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'
</script>

<template>
  <header>
    <a href="books.html"><img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" /></a>

    <div class="wrapper">
<!--      <HelloWorld msg="Crear libro!" />-->
    </div>
  </header>

  <main>
    <HelloWorld msg="Create Book" />
<!--    <TheWelcome />-->
    <form  action="http://localhost:8084/create-book-entry" method="post" target="_self">
      <label for="fname">Book name: </label>
      <input type="text" id="fname" name="fname"><br><br>
      <label for="lname">Book Author: </label>
      <input type="text" id="lname" name="lauthor"><br><br>
      <label for="lid">Book Id: </label>
      <input type="text" id="lid" name="lid"><br><br>
      <input type="submit" value="Submit">
    </form>
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

<script >

</script>