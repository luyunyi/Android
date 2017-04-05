package com.example.dell.dialog_menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout loginForm = (LinearLayout) getLayoutInflater().inflate(R.layout.login, null);

        Button bnt1 = (Button) findViewById(R.id.bnt_alertDialog);
        bnt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setView(loginForm)
                            .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //此处可执行登录处
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //取消登录，不做任何事情
                                }
                            })
                            .create().show();
                }
        });

        Button bnt2 = (Button) findViewById(R.id.bnt_menuTest);
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuTestActivity.class);
                startActivity(intent);
            }
        });

        Button bnt3 = (Button) findViewById(R.id.bnt_contextMenu);
        bnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContextMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
