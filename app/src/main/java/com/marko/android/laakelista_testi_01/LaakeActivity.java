package com.marko.android.laakelista_testi_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class LaakeActivity extends AppCompatActivity {


    //private boolean[] lokerikko; <.lokerikkoa ei tarvita?
    //ALUSTETAAN SWITCHEJA...
    //Maanantai aamun Switchi + loput switchit
    Switch ma_aa_switch, ti_aa_switch, ke_aa_switch, to_aa_switch, pe_aa_switch, la_aa_switch, su_aa_switch;
    Switch ma_pa_switch, ti_pa_switch, ke_pa_switch, to_pa_switch, pe_pa_switch, la_pa_switch, su_pa_switch;
    Switch ma_il_switch,ti_il_switch,ke_il_switch,to_il_switch,pe_il_switch,la_il_switch,su_il_switch;


    Intent entti;
    //laakkeen paikka, minkä avulla oikea laake etsitään Singletonin listalta
    protected int laakkeenPaikka;
    TextView laakkeenNimi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laake);

        entti = getIntent();

        //Laakkeen nimi haetaan: Laakelistainstanssista, lääkelistasta, josta haetaan laake päivä-Activitysta saadulla indeksillä, ja lääkkeeltä otetaan getNimi
        //PäiväActivityssa sama juttu helpompana, indeksi on oma muuttujansa, jonka perusteella sitten haetaan laake instanssista..

        String nimi = entti.getStringExtra(PaivaActivity.EXTRA_LAAKENIMI);//<-säilytetään, vaikka ei ehkä tarvitsisi, nopeampi kirjoittaa kuin PaivaActivity.EXTRA*****...


        Log.d("Logi", " Laake Activity laakkeen nimi on " + nimi);
        if (nimi == null) {
            Laakelista.getInstance().lisaaLaake("");
            laakkeenPaikka = Laakelista.getInstance().getLaakkeet().size()-1;
            Log.d("Logi", "uuden lääkkeen paikka: " + laakkeenPaikka);
        }else {
            laakkeenPaikka = Laakelista.getInstance().etsiPaikkaListasta(nimi); //<- voisi olla myös nimi(just yläpuolella)=
            Log.d("Logi", "laake Activity listalta haettiin "+ laakkeenPaikka);
        }

        //etitään ruudusta tekstialue missä nimen voi näyttää
        laakkeenNimi = findViewById(R.id.plainTextLaakeNimi);
        laakkeenNimi.setText(nimi);

        //etsitään kaikki 21 perus-switchiä
        ma_aa_switch = findViewById(R.id.laakeswitch_aamu_ma);
        ti_aa_switch = findViewById(R.id.laakeswitch_aamu_ti);
        ke_aa_switch = findViewById(R.id.laakeswitch_aamu_ke);
        to_aa_switch = findViewById(R.id.laakeswitch_aamu_to);
        pe_aa_switch = findViewById(R.id.laakeswitch_aamu_pe);
        la_aa_switch = findViewById(R.id.laakeswitch_aamu_la);
        su_aa_switch = findViewById(R.id.laakeswitch_aamu_su);

        ma_pa_switch = findViewById(R.id.laakeswitch_paiva_ma);
        ti_pa_switch = findViewById(R.id.laakeswitch_paiva_ti);
        ke_pa_switch = findViewById(R.id.laakeswitch_paiva_ke);
        to_pa_switch = findViewById(R.id.laakeswitch_paiva_to);
        pe_pa_switch = findViewById(R.id.laakeswitch_paiva_pe);
        la_pa_switch = findViewById(R.id.laakeswitch_paiva_la);
        su_pa_switch = findViewById(R.id.laakeswitch_paiva_su);

        ma_il_switch = findViewById(R.id.laakeswitch_ilta_ma);
        ti_il_switch = findViewById(R.id.laakeswitch_ilta_ti);
        ke_il_switch = findViewById(R.id.laakeswitch_ilta_ke);
        to_il_switch = findViewById(R.id.laakeswitch_ilta_to);
        pe_il_switch = findViewById(R.id.laakeswitch_ilta_pe);
        la_il_switch = findViewById(R.id.laakeswitch_ilta_la);
        su_il_switch = findViewById(R.id.laakeswitch_ilta_su);

        paivitaScreeni();
    }



    //TODO main switch kattoo onko se itse on/off, ja sen perusteella antaa parametriksi true/false, joka jatkuu myös switchTheSwitchiin,
    // ja Laake itsekin tarvitsee vaan yhden metodin silloin joka saa saman true/false parametrin..

    public void switchTheSwitch(View v){
        int i = 0;
        //.... sanotaan että v on swtichi nimeltä sw... jotta voidaan nähdä onko se checked.... ..
        Switch sw = (Switch) v;
        int switchId = v.getId();
        switch (switchId) {
            //annetaan id, perustuen käännettyyn switchiin
            //aamut
            case R.id.laakeswitch_aamu_ma:
                i = 0;
                break;
            case R.id.laakeswitch_aamu_ti:
                i=3;
                break;
            case R.id.laakeswitch_aamu_ke:
                i=6;
                break;
            case R.id.laakeswitch_aamu_to:
                i=9;
                break;
            case R.id.laakeswitch_aamu_pe:
                i=12;
                break;
            case R.id.laakeswitch_aamu_la:
                i=15;
                break;
            case R.id.laakeswitch_aamu_su:
                i=18;
                break;

            //PÄIVÄT
            case R.id.laakeswitch_paiva_ma:
                i=1;
                break;
            case R.id.laakeswitch_paiva_ti:
                i=4;
                break;
            case R.id.laakeswitch_paiva_ke:
                i=7;
                break;
            case R.id.laakeswitch_paiva_to:
                i=10;
                break;
            case R.id.laakeswitch_paiva_pe:
                i=13;
                break;
            case R.id.laakeswitch_paiva_la:
                i=16;
                break;
            case R.id.laakeswitch_paiva_su:
                i=19;
                break;

            //ILLAT
            case R.id.laakeswitch_ilta_ma:
                i=2;
                break;
            case R.id.laakeswitch_ilta_ti:
                i=5;
                break;
            case R.id.laakeswitch_ilta_ke:
                i=8;
                break;
            case R.id.laakeswitch_ilta_to:
                i=11;
                break;
            case R.id.laakeswitch_ilta_pe:
                i=14;
                break;
            case R.id.laakeswitch_ilta_la:
                i=17;
                break;
            case R.id.laakeswitch_ilta_su:
                i=20;
                break;

        }
        muutaAika(i, sw.isChecked());
    }

    //tämä komento etsii oikean lääkkeen singletonista, ja muuttaa oikeaa aikaa true tai false
    public void muutaAika(int indeksi,boolean muutos) {
        Log.d("Logi", "muutetaan aikaa " + indeksi + muutos + Laakelista.getInstance().getLaake(laakkeenPaikka).getNimi());
        //Laakelista.getInstance().getLaake(Integer.parseInt(laakeIndeksi)).muutaAika(indeksi, muutos);
        Laakelista.getInstance().getLaake(laakkeenPaikka).muutaAika(indeksi, muutos);
    }
    //LääkeActivityn Aamut-switch aktivoi/poistaa kaikki aamut
    public void muutaAamut(View v) {
        Switch sw = (Switch) v;
        for (int i = 0; i <19; i=i+3) {
            muutaAika(i,sw.isChecked());
             paivitaScreeni();
        }
    }
    public void muutaPaivat(View v) {
        Switch sw = (Switch) v;
        for (int i = 1; i <20; i=i+3) {
            muutaAika(i,sw.isChecked());
            //Log.d("Logi", i + " on " + sw.isChecked());
            paivitaScreeni();
        }
    }
    public void muutaIllat(View v) {
        Switch sw = (Switch) v;
        for (int i = 2; i <21; i=i+3) {
            muutaAika(i,sw.isChecked());
            //Log.d("Logi", i + " on " + sw.isChecked());
            paivitaScreeni();
        }
    }


    public void paivitaScreeni() {
        //paivitetaan koko screenin switchit...
        ma_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(0));
        ti_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(3));
        ke_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(6));
        to_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(9));
        pe_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(12));
        la_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(15));
        su_aa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(18));

        ma_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(1));
        ti_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(4));
        ke_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(7));
        to_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(10));
        pe_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(13));
        la_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(16));
        su_pa_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(19));

        ma_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(2));
        ti_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(5));
        ke_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(8));
        to_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(11));
        pe_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(14));
        la_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(17));
        su_il_switch.setChecked(Laakelista.getInstance().getLaake(laakkeenPaikka).getAika(20));
    }

    @Override
    public void onBackPressed() {
        //katsotaan onko lääkkeellä enää yhtään päivää, jos ei ole, poistetaan lääke listalta
        Laakelista.getInstance().tarkistaLaake(laakkeenPaikka);
        super.onBackPressed();

    }


}