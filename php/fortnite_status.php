<?php

class Fortnite {

   private $curl;
   private $header;

   private $json;

    public function __construct($nome) {
        $this->curl = curl_init();
        $this->header = [
            'TRN-Api-Key: b26a2a33-9588-4e91-9197-de38370c3b66'
        ];

        $this->conectar($nome);
        $this->json = curl_exec($this->curl);

        $this->json = json_decode($this->json, true);

        curl_close($this->curl);

    }

    public function getKills() {
        return $this->json['lifeTimeStats'][10]['value'];
    }

    public function getWins() {
        return $this->json['lifeTimeStats'][8]['value'];
    }
    

    private function conectar($nick_name) {
        curl_setopt($this->curl, CURLOPT_URL, "https://api.fortnitetracker.com/v1/profile/pc/$nick_name");
        curl_setopt($this->curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($this->curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($this->curl, CURLOPT_HTTPHEADER, $this->header);
    }

}

if ($_GET['tipo'] == "kills")
  print (new Fortnite("Danillo Dantas"))->getKills();

if ($_GET['tipo'] == "wins")
  print (new Fortnite("Danillo Dantas"))->getWins();


?>
