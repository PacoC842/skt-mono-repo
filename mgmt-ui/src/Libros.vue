<script setup>
import HelloWorld from './components/HelloWorld.vue'</script>

<template>
  <header>


  </header>

  <main>
    <div>
      <br>
      <br>

      <HelloWorld msg="List Books"/>
      <br>
      <br>
      <br>

      <a href="index.html"><img alt="Vue logo" class="logo" height="125" src="./assets/logo.svg"/></a>
      <!--    <TheWelcome />-->
      <div v-for="item in listItems">
        <h5>
          Book: {{ item.name }} <br> Author: {{ item.author }}
        </h5>
      </div>
    </div>
  </main>
</template>

<style>

div {

}

h5 {
  /* Add shadows to create the "card" effect */
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  transition: 0.3s;
  border: 10px;
  margin: 10px;
}

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
  data() {
    return {
      listItems: []
    }
  },
  methods: {
    async getData() {
      const res = await fetch("http://localhost:8084/books");
      const finalRes = await res.json();
      this.listItems = finalRes;
    }
  },
  mounted() {
    this.getData()
  }
}
</script>