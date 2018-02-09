package com.softorea.schoolsen.lists;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Softorea on 10/31/2017.
 */

public class Enrolftpstr {

    public static String khindkod, khindkor, khindkof, khindkos, khindkoret, kkhoward, kkhowarr, kkhowarf, kkhowars, kkhowarret, kmathd, kmathr, kmathf, kmaths, kmathret, kabcd, kabcr, kabcf, kabcs, kabcret, kpashtod, kpashtor, kpashtof, kpashtos, kpashtoret, kserakid, kserakir, kserakif, kserakis, kserakiftp, kurdud, kurdur, kurduf, kurdus, kurduret, oneengd, oneengr, oneengf, oneengs, oneengret, oneknwd, oneknwr, oneknwf, oneknws, oneknwret, onemathd, onemathr, onemathf, onemaths, onemathret, onepashtod, onepashtor, onepashtof, onepashtos, onepashtoret, twourdud, twourdur, twourduf, twourdus, twourduret, twoenglishd, twoenglishr, twoenglishf, twoenglishs, twoenglishret, twowburdud, twowburdur, twowburduf, twowburdus, twowburduret, twopashtod, twopashtor, twopashtof, twopashtos, twopashtoret,
            twowbpashtod, twowbpashtor, twowbpashtof, twowbpashtos, twowbpashtoret, twomathsd, twomathsr, twomathsf, twomathss, twomathsret, twowbmathsd, twowbmathsr, twowbmathsf, twowbmathss, twowbmathsret, twoknowd, twoknowr, twoknowf, twoknows, twoknowret;

    public void execute(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("STOREDVALUES", Activity.MODE_PRIVATE);

        khindkod = preferences.getString("kHindoQaidaDemanded", "");
        khindkor = preferences.getString("kHindoQaidaReceived", "");
        khindkof = preferences.getString("kHindoQaidaftb", "");
        khindkos = preferences.getString("kHindoQaidasurplus", "");
        khindkoret = preferences.getString("kHindoQaidareturn", "");

        kkhoward = preferences.getString("kKhowarQaidaDemanded", "");
        kkhowarr = preferences.getString("kKhowarQaidaReceived", "");
        kkhowarf = preferences.getString("kKhowarQaidaftb", "");
        kkhowars = preferences.getString("kKhowarQaidasurplus", "");
        kkhowarret = preferences.getString("kKhowarQaidareturn", "");

        kmathd = preferences.getString("kMathsQaidaDemanded", "");
        kmathr = preferences.getString("kMathsQaidaReceived", "");
        kmathf = preferences.getString("kMathsQaidaftb", "");
        kmaths = preferences.getString("kMathsQaidasurplus", "");
        kmathret = preferences.getString("kMathsQaidareturn", "");

        kabcd = preferences.getString("kABCQaidaDemanded", "");
        kabcr = preferences.getString("kABCQaidaReceived", "");
        kabcf = preferences.getString("kABCQaidaftb", "");
        kabcs = preferences.getString("kABCQaidasurplus", "");
        kabcret = preferences.getString("kABCQaidareturn", "");

        kpashtod = preferences.getString("kPashtoQaidaDemanded", "");
        kpashtor = preferences.getString("kPashtoQaidaReceived", "");
        kpashtof = preferences.getString("kPashtoQaidaftb", "");
        kpashtos = preferences.getString("kPashtoQaidasurplus", "");
        kpashtoret = preferences.getString("kPashtoQaidareturn", "");

        kserakid = preferences.getString("kSerakiQaidaDemanded", "");
        kserakir = preferences.getString("kSerakiQaidaReceived", "");
        kserakif = preferences.getString("kSerakiQaidaftb", "");
        kserakis = preferences.getString("kSerakiQaidasurplus", "");
        kserakiftp = preferences.getString("kSerakiQaidareturn", "");

        kurdud = preferences.getString("kUrduQaidaDemanded", "");
        kurdur = preferences.getString("kUrduQaidaReceived", "");
        kurduf = preferences.getString("kUrduQaidaftb", "");
        kurdus = preferences.getString("kUrduQaidasurplus", "");
        kurduret = preferences.getString("kUrduQaidareturn", "");

        oneengd = preferences.getString("1EnglishDemanded", "");
        oneengr = preferences.getString("1EnglishReceived", "");
        oneengf = preferences.getString("1Englishftb", "");
        oneengs = preferences.getString("1Englishsurplus", "");
        oneengret = preferences.getString("1Englishreturn", "");

        oneknwd = preferences.getString("1KnowledgeDemanded", "");
        oneknwr = preferences.getString("1KnowledgeReceived", "");
        oneknwf = preferences.getString("1Knowledgeftb", "");
        oneknws = preferences.getString("1Knowledgesurplus", "");
        oneknwret = preferences.getString("1Knowledgereturn", "");

        onemathd = preferences.getString("1MathsDemanded", "");
        onemathr = preferences.getString("1MathsReceived", "");
        onemathf = preferences.getString("1Mathsftb", "");
        onemaths = preferences.getString("1Mathssurplus", "");
        onemathret = preferences.getString("1Mathsreturn", "");

        onepashtod = preferences.getString("1PashtoDemanded", "");
        onepashtor = preferences.getString("1PashtoReceived", "");
        onepashtof = preferences.getString("1Pashtoftb", "");
        onepashtos = preferences.getString("1Pashtosurplus", "");
        onepashtoret = preferences.getString("1Pashtoreturn", "");

        twoenglishd = preferences.getString("2EnglishDemanded", "");
        twoenglishr = preferences.getString("2EnglishReceived", "");
        twoenglishf = preferences.getString("2Englishftb", "");
        twoenglishs = preferences.getString("2Englishsurplus", "");
        twoenglishret = preferences.getString("2Englishreturn", "");

        twourdud = preferences.getString("2UrduDemanded", "");
        twourdur = preferences.getString("2UrduReceived", "");
        twourduf = preferences.getString("2Urduftb", "");
        twourdus = preferences.getString("2Urdusurplus", "");
        twourduret = preferences.getString("2Urdureturn", "");

        twowburdud = preferences.getString("2WBUrduDemanded", "");
        twowburdur = preferences.getString("2WBUrduReceived", "");
        twowburduf = preferences.getString("2WBUrduftb", "");
        twowburdus = preferences.getString("2WBUrdusurplus", "");
        twowburduret = preferences.getString("2WBUrdureturn", "");

        twopashtod = preferences.getString("2PashtoDemanded", "");
        twopashtor = preferences.getString("2PashtoReceived", "");
        twopashtof = preferences.getString("2Pashtoftb", "");
        twopashtos = preferences.getString("2Pashtosurplus", "");
        twopashtoret = preferences.getString("2Pashtoreturn", "");

        twowbpashtod = preferences.getString("2WBPashtoDemanded", "");
        twowbpashtor = preferences.getString("2WBPashtoReceived", "");
        twowbpashtof = preferences.getString("2WBPashtoftb", "");
        twowbpashtos = preferences.getString("2WBPashtosurplus", "");
        twowbpashtoret = preferences.getString("2WBPashtoreturn", "");

        twomathsd = preferences.getString("2MathsDemanded", "");
        twomathsr = preferences.getString("2MathsReceived", "");
        twomathsf = preferences.getString("2Mathsftb", "");
        twomathss = preferences.getString("2Mathssurplus", "");
        twomathsret = preferences.getString("2Mathsreturn", "");

        twowbmathsd = preferences.getString("2WBMathsDemanded", "");
        twowbmathsr = preferences.getString("2WBMathsReceived", "");
        twowbmathsf = preferences.getString("2WBMathsftb", "");
        twowbmathss = preferences.getString("2WBMathssurplus", "");
        twowbmathsret = preferences.getString("2WBMathsreturn", "");

        twoknowd = preferences.getString("2KnowledgeDemanded", "");
        twoknowr = preferences.getString("2KnowledgeReceived", "");
        twoknowf = preferences.getString("2Knowledgeftb", "");
        twoknows = preferences.getString("2Knowledgesurplus", "");
        twoknowret = preferences.getString("2Knowledgereturn", "");


    }

}
