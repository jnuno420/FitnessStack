<template>
  <div class="notFound">
    <v-app-bar color="#000314" elevate-on-scroll dark fixed>
      <div class="d-flex align-center">
        <router-link to="/">
          <v-img
            alt="Fitness Stack Logo"
            class="shrink mr-2 ml-8"
            contain
            src="@/assets/fitness_stack_logo.png"
            transition="scale-transition"
            width="180"
          />
        </router-link>
      </div>
    </v-app-bar>
    <v-container>
      <v-row>
        <v-col class="text-center mt-16 pt-16" cols="12" md="6">
          <h1 style="color: #7189ff;font-size: 40px">
            Perdeu-se?
          </h1>
          <div class="my-10 mx-16">
            <p>Não foi possível encontrar a página. Lamentamos o transtorno.</p>
          </div>
          <v-btn color="#7189ff" dark :href="'/'">Voltar à Home</v-btn>
        </v-col>
        <v-col cols="12" md="6">
          <v-img
            :src="require('../assets/page_not_found.png')"
            contain
            height="90%"
          />
        </v-col>
      </v-row>
    </v-container>
    <Footer />
  </div>
</template>

<script>
import Footer from "@/components/Footer.vue";

export default {
  name: "NotFound",
  components: {
    Footer,
  },
  created() {
    document.title = "404 Not Found";
  },
};
</script>

<style>
.notFound {
  background-color: #d3d3d2;
}
</style>