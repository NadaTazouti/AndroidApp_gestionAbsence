package com.example.ledger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Intro extends AppCompatActivity {
    private TextView mtitre1;
    private TextView mtitre2;
    private ImageView mImage;


    private Button mbutton2suiv;
    private Question[] mTitreBank = new Question[]{
                    new Question(R.string.nouveaute), new Question(R.string.dessin0),
            };
    private Question[] mTextBank = new Question[]{
            new Question(R.string.books), new Question(R.string.dessin1),
    };
    private Question[] mImageBank = new Question[]{
            new Question(R.drawable.books), new Question(R.drawable.dessins),
    };



    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        mtitre1=(TextView)findViewById(R.id.titre1);
        mtitre2=(TextView)findViewById(R.id.titre2);
        mImage=(ImageView)findViewById(R.id.imageView2);
        int titre = mTitreBank[mCurrentIndex].getMtextoResId();
        int texte= mTextBank[mCurrentIndex].getMtextoResId();
        int image= mImageBank[mCurrentIndex].getMtextoResId();
        mtitre1.setText(titre);
        mtitre2.setText(texte);
        mbutton2suiv =(Button)findViewById(R.id.button2suiv);
        mbutton2suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mTextBank.length;
                int question0 = mTitreBank[mCurrentIndex].getMtextoResId();
                mtitre1.setText(question0);
                int question1 = mTextBank[mCurrentIndex].getMtextoResId();
                mtitre2.setText(question1);
                int question2= mImageBank[mCurrentIndex].getMtextoResId();
                mImage.setImageResource(question2);
                mCurrentIndex=mCurrentIndex-1;
            }
        });


    }
}
