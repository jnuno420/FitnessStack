<template>
  <div class="alunos">
    <NavBar />
    <v-row>
      <v-col cols="12" md="1">
        <SideBar />
      </v-col>
      <v-col
        cols="12"
        md="10"
        style="padding-top: 120px; padding-bottom: 100px"
      >
        <v-dialog
          v-model="dialog"
          fullscreen
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn class="mx-6" v-bind="attrs" v-on="on" color="#f95738" dark>
              As minhas solicitações <v-chip v-if="titles.length!=0" class="ml-2" small color="#7189ff">{{ titles.length }}</v-chip>
            </v-btn>
          </template>
          <template v-slot:default="dialog">
            <v-card class="pa-0 ma-0">
              <v-toolbar color="#f95738" dark
                ><h3>Solicitações</h3>
                <v-spacer></v-spacer>
                <v-btn icon @click="dialog.value = false"
                  ><v-icon>mdi-close</v-icon></v-btn
                >
              </v-toolbar>

              <AlunoSolicitacao />
            
            </v-card>
          </template>
        </v-dialog>
        <v-container class="pa-0 ma-0"> <Aluno /></v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_Treinador.vue";
import Aluno from "@/components/Aluno.vue";
import AlunoSolicitacao from "@/components/AlunoSolicitacao.vue";
import axios from "axios";

export default {
  name: "Alunos",
  components: {
    NavBar,
    SideBar,
    Aluno,
    AlunoSolicitacao,
  },
  data: () => ({
    dialog: false,
    dialog2: false,
    titles: [],
  }),
  created() {
    document.title = "Alunos";
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await axios.get(this.url, {headers: {'token': localStorage.getItem("token")}});
      this.titles = response.data;   
    },
  },
  computed: {
    url() {
      return process.env.VUE_APP_BASELINK+"/api/treinador/getAlunosPendentes"
    },
  },
};
</script>

<style>
.alunos {
  background-color: #d3d3d2;
}
</style>