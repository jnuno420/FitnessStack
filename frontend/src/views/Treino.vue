<template>
  <div class="profile">
    <NavBar />
    <v-row>
    <v-col v-if="getUsertype() == 1" cols="12" md="1">
        <SideBarTreinador />
      </v-col>
      <v-col v-else cols="12" md="1">
        <SideBar />
      </v-col>
    </v-row>
    <v-row style="padding-top: 90px">
      <v-col cols="12" md="1"> </v-col>
      <v-col cols="12" md="5">
        <v-card>
          <v-toolbar flat rounded color="#f95738" dark class=""
            ><v-spacer></v-spacer
            ><v-toolbar-title class="ml-2">{{
              treino.nome
            }}</v-toolbar-title
            ><v-spacer></v-spacer>
          </v-toolbar>
          <v-list two-line>
            <v-list-item class="ml-8">
              <v-list-item-avatar size="60">
                <v-img :src="linkapi() + treino.criador_foto"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title>{{ treino.criador }}</v-list-item-title>
                <v-list-item-subtitle
                  >Criado em {{ treino.data }}</v-list-item-subtitle
                >
              </v-list-item-content>
            </v-list-item>
            <v-divider class="mx-8"></v-divider>
            <v-list-item class="ml-4">
              <v-list-item-content class="mx-auto px-4 pt-4 pb-3">
                <v-list-item-title class="mb-2"
                  ><b>Descrição:</b> {{ treino.descricao }}</v-list-item-title
                >
                <v-list-item-title class="mb-2"
                  ><b>Categorias: </b>
                  <v-chip
                    class="mr-1"
                    v-for="categoria in treino.categorias"
                    :key="categoria"
                    >{{ categoria }}</v-chip
                  ></v-list-item-title
                >
                <v-list-item-title class="mb-4"
                  ><b>Dificuldade:</b>
                  {{ treino.dificuldade }}</v-list-item-title
                >
                <v-list-item-title class="mb-4"
                  ><b>Duração:</b> {{ treino.duracao }}</v-list-item-title
                >
                <v-list-item-title class="mb-4"
                  ><b>Código:</b> {{ $route.params.codigo }}</v-list-item-title
                >
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
        <v-card
          v-scroll.self="onScroll"
          style="margin-right: 90px max-width:700px"
          class="mt-4 pt-2 overflow-y-auto"
          max-height="400"
        >
          <h2 class="text-center" style="color: #f95738">Avaliações</h2>
          <v-divider class="mt-3"></v-divider>
          <div
            class="text-center"
            v-for="(item, i) in treino.avaliacoes"
            :key="i"
          >
            <v-list three-line>
              <v-list-item class="ml-8">
                <!-- <v-list-item-icon class="mx-0">
                  <v-icon x-large>mdi-account-circle</v-icon>
                </v-list-item-icon> -->
                <v-list-item-avatar size="50">
                <v-img :src="linkapi() + item.foto_avaliador"></v-img>
              </v-list-item-avatar>

                <v-list-item-content>
                  <v-card-title class="subtitle-2 my-0 py-0 pb-2">{{
                    item.username
                  }}</v-card-title>
                  <v-card-text>
                    <v-row align="center" class="mx-0">
                      <v-rating
                        readonly
                        dense
                        v-model="item.avaliacao"
                        background-color="black"
                        color="#f95738"
                        size="14"
                      ></v-rating>
                    </v-row>
                  </v-card-text>
                  <v-card-text>
                    <v-row align="center" class="mx-0">{{
                      item.comentario
                    }}</v-row></v-card-text
                  >
                </v-list-item-content>
              </v-list-item>
              <v-divider
                v-if="i + 1 < Object.keys(treino.avaliacoes).length"
                class="mx-8"
              ></v-divider>
            </v-list>
          </div>
        </v-card>
      </v-col>
      <v-col cols="12" md="5">
        <v-card
          v-scroll.self="onScroll"
          style="color: #f95738"
          class="pt-4 overflow-y-auto"
          max-height="800"
        >
          <h2 class="text-center">Exercícios</h2>
          <v-divider class="mt-3"></v-divider>

          <v-simple-table height="300px">
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-center"></th>
                  <th class="text-center">Nome</th>
                  <th class="text-center">Séries</th>
                  <th class="text-center">Repetições/Duração</th>
                  <th class="text-center">Descanso</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  class="text-center"
                  v-for="(item,i) in treino.exercicios"
                  :key="i"
                >
                <td>
                <v-dialog transition="dialog-bottom-transition" width="1000">
                <template v-slot:activator="{ on, attrs }">
                <v-btn
                  v-bind="attrs"
                    v-on="on"
                  class="mx-2"
                  icon
                  dark
                  small
                  color="pink"
                ><v-icon color="#f95738">mdi-text-box-search</v-icon>
                </v-btn>
                </template>
                <template v-slot:default="dialog">
                  <v-card>
                    <v-toolbar color="#f95738" dark
                      ><h3>{{ item.nome }}</h3>
                      <v-spacer></v-spacer>
                      <v-btn icon @click="dialog.value = false"
                        ><v-icon>mdi-close</v-icon></v-btn
                      >
                    </v-toolbar>
                    <v-card-text>
                      <v-row class="w-100">
                        <v-col cols="12" md="3">
                          <div
                            class="d-flex flex-no-wrap justify-space-between"
                          >
                            <div class="mt-6">
                              <v-list-item two-line>
                                <v-list-item-content>
                                  <v-list-item-title
                                    >Descrição</v-list-item-title
                                  >
                                  <v-list-item-subtitle>{{
                                    item.descricao
                                  }}</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>

                              <v-list-item two-line>
                                <v-list-item-content>
                                  <v-list-item-title
                                    >Material necessário</v-list-item-title
                                  >
                                  <v-list-item-subtitle>{{
                                    item.material
                                  }}</v-list-item-subtitle>
                                </v-list-item-content>
                              </v-list-item>
                            </div>
                          </div>
                        </v-col>

                        <v-col md="9">
                          <div>
                            <v-carousel
                              :show-arrows="item.fotos.length>1"
                              class="mt-6"
                              width="auto"
                                  height="400"
                            >
                              <v-carousel-item
                                v-for="(item1, i1) in item.fotos"
                                :key="i1"
                              >
                                <template v-if="item1.includes('photo')">
                                  <img width="auto"
                                  height="400" contain :src="linkfoto() + item1" />
                                </template>
                                <template v-else>
                                  <video width="auto"
                                        height="400"
                                        contain controls>
                                    <source
                                      :src="linkfoto() + item1"
                                      type="video/mp4"
                                    />
                                  </video>
                                </template>
                              </v-carousel-item>
                            </v-carousel>
                          </div>
                        </v-col>
                      </v-row>
                    </v-card-text>
                  </v-card>
                </template>
              </v-dialog>
              </td>
                  <td>{{ item.nome }}</td>
                  <td>{{ item.series }}</td>
                  <td v-if="!item.tipo">{{ item.repeticoes }}s</td>
                  <td v-else>{{ item.repeticoes }} reps</td>
                  <td>{{ item.descanso }}s</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>

        <div v-if="getUsertype() == 0" class="text-center my-16">
          <v-btn width="180" v-on:click="iniciarTreino()" color="#f95738" dark
            >Iniciar treino</v-btn
          >
          <v-container class="text-center">
            <v-dialog v-model="dialog1" persistent max-width="380px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn width="180" color="#f95738" dark v-bind="attrs" v-on="on"
                  >Avaliar treino</v-btn
                >
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline text-center"
                    >Dê-nos o seu feedback acerca deste treino!</span
                  >
                </v-card-title>
                <v-card-text>
                  <div class="text-center mt-12">
                    <v-rating
                      v-model="rating"
                      color="#f95738"
                      background-color="grey darken-1"
                      empty-icon="$ratingFull"
                      half-increments
                      hover
                      large
                    ></v-rating>
                    <v-text-field
                      color="#f95738"
                      v-model="comentario"
                      label="Deixe-nos o seu comentário."
                      required
                    >
                    </v-text-field>
                  </div>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="#f95738" text @click="dialog1 = false"
                    >Sair</v-btn
                  >
                  <v-btn
                    color="#f95738"
                    dark
                    @click="dialog1 = false"
                    v-on:click="submit()"
                  >
                    Confirmar
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-container>
        </div>
      </v-col>
    </v-row>
  </div>
</template>
<script>
import NavBar from "@/components/NavBar_Logged.vue";
import SideBar from "@/components/SideBar_User.vue";
import SideBarTreinador from "@/components/SideBar_Treinador.vue";
import axios from "axios";

export default {
  name: "Treino",
  components: {
    NavBar,
    SideBar,
    SideBarTreinador
  },
  data() {
    return {
      scrollInvoked: 0,
      dialog1: false,
      rating: "",
      comentario: "",
      avaliacoes: [],
      treino: {
        nome: "",
        duracao: "",
        categorias: [{ categoria: "" }],
        dificuldade: "",
        criador: "",
        criador_foto: "",
        //treinador: "",
        data: "",
        exercicios: [
          {
            nome: "",
            tipo: "",
            series: 0,
            repeticoes: 0,
            descanso: 0,
            fotos: [],
          },
        ],
        
      },
      /* exercicios: [], */
    };
  },
  methods: {
    linkfoto() {
      return process.env.VUE_APP_BASELINK;
    },
    getUsertype() {
      return localStorage.getItem("usertype");
    },
    linkapi() {
      return process.env.VUE_APP_BASELINK;
    },
    iniciarTreino: function () {
      //this.$router.push("/iniciarTreino/" + this.$route.params.codigo);
      this.$router.push({
        name: "IniciarTreino",
        path: "iniciarTreino/" + this.$route.params.codigo,
      });
    },
    onScroll() {
      this.scrollInvoked++;
    },
    submit() {
      //var cod_treino = this.$route.params.codigo;
      console.log(this.rating);
      console.log(this.comentario);
      var post_body = {
        username: localStorage.getItem("username"),
        cod_treino: this.$route.params.codigo,
        avaliacao: this.rating,
        comentario: this.comentario,
      };
      this.treino.avaliacoes.push({
        username: localStorage.getItem("username"),
        avaliacao: this.rating,
        comentario: this.comentario,
      });
      axios
        .post(
          process.env.VUE_APP_BASELINK + "/api/treinos/avaliacao",
          post_body,
          { headers: { token: localStorage.getItem("token") } }
        )
        .then((response) => {
          console.log(response);
        });
    },
  },
  mounted() {
    axios
      .get(
        process.env.VUE_APP_BASELINK +
          "/api/treinos/getTreino?codigo=" +
          this.$route.params.codigo,
        { headers: { token: localStorage.getItem("token") } }
      )
      .then((response) => {
        this.treino = response.data;
        console.log(this.treino);
        console.log("heijsfidjs" + JSON.stringify(this.treino));
      })
      .finally(() => (this.loading = false));

      
   /* axios
       .get(process.env.VUE_APP_BASELINK + "/api/exercicio/listar", {
        headers: { token: localStorage.getItem("token") },
       })
       .then((response) => {
         this.exercicios = response.data;
      }); */
  
  },
};
</script>