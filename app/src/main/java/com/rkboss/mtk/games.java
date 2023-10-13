package com.rkboss.mtk;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class games extends AppCompatActivity {
    ImageView closeImageView;


    LinearLayout single;
    LinearLayout jodi;
    LinearLayout singlepatti;
    LinearLayout doublepatti;
    LinearLayout tripepatti;
    LinearLayout halfsangam;
    LinearLayout fullsangam;
    private ImageView crossing;
    TextView title, timing;
    ArrayList<String> number = new ArrayList<>();
    String market = "", is_open = "0", is_close = "0";
    private ImageView back;
    private LinearLayout oddEven;
    LinearLayout redBracket;
    LinearLayout cyclePanna, familyJodi, redFamilyJodi, familypannel;
    private LinearLayout spdptp;
    private LinearLayout spMotor;
    private LinearLayout dpMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        initViews();
        market = getIntent().getStringExtra("market");
        is_open = getIntent().getStringExtra("is_open");
        is_close = getIntent().getStringExtra("is_close");


//        LinearLayout singleLinearLayout = findViewById(R.id.single);
//        singleLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "single")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "single")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });
//

//        LinearLayout jodiLinearLayout = findViewById(R.id.jodi);
//        jodiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "jodi")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        // Add your code to handle "CLOSE" option click here
//                        // For example, you can perform some action or dismiss the dialog
//
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "jodi")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout redbracketLinearLayout = findViewById(R.id.red_bracket);
//        redbracketLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, RedBracket.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "jodi")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, RedBracket.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "jodi")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout singlepattiLinearLayout = findViewById(R.id.singlepatti);
//        singlepattiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });
//
//
//        LinearLayout doublepattiLinearLayout = findViewById(R.id.doublepatti);
//        doublepattiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "doublepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "doublepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout tripepattiLinearLayout = findViewById(R.id.tripepatti);
//        tripepattiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "triplepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, single_bet.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "triplepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout spdptpLinearLayout = findViewById(R.id.spdptp);
//        spdptpLinearLayout.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, SpDpTp.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, SpDpTp.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout cyclePannaLinearLayout = findViewById(R.id.cyclePanna);
//        cyclePannaLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, CyclePanna.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Cycle Patti")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, CyclePanna.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Cycle Patti")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });

//        LinearLayout familypannelLinearLayout = findViewById(R.id.familypannel);
//        familypannelLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, FamilyPannel.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Family Panna")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, FamilyPannel.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Family Panna")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout familyJodiLinearLayout = findViewById(R.id.familyJodi);
//        familyJodiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, FamilyJodi.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Family Jodi")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, FamilyJodi.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Family Jodi")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });

//        LinearLayout redFamilyJodiLinearLayout = findViewById(R.id.redFamilyJodi);
//        redFamilyJodiLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, RedFamilyJodi.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Red Family Jodi")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, RedFamilyJodi.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "Red Family Jodi")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout halfsangamLinearLayout = findViewById(R.id.halfsangam);
//        halfsangamLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, halfsangam.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "halfsangam")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, halfsangam.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "halfsangam")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout fullsangamLinearLayout = findViewById(R.id.fullsangam);
//        fullsangamLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, fullsangam.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "fullsangam")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, fullsangam.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "fullsangam")
//                                .putExtra("list", number)
//                                .putExtra("open_av", is_open)
//                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        );

//                    }
//                });
//
//                dialog.show();
//            }
//        });


//        LinearLayout spmotorLinearLayout = findViewById(R.id.sp_motor);
//        spmotorLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, SpMotor.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, SpMotor.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "singlepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });

//        LinearLayout dpmotorLinearLayout = findViewById(R.id.dp_motor);
//        dpmotorLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
//
//                // Set the custom layout for the dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//                builder.setView(dialogView);
//
//                AlertDialog dialog = builder.create();
//
//                // Set the onClickListener for the "OPEN" option
//                TextView openTextView = dialogView.findViewById(R.id.openTextView);
//                openTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Add your code to handle "OPEN" option click here
//                        // For example, you can perform some action or dismiss the dialog
//                        startActivity(new Intent(games.this, SpMotor.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "doublepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                // Set the onClickListener for the "CLOSE" option
//                TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
//                closeTextView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(games.this, SpMotor.class)
//                                .putExtra("market", market)
//                                .putExtra("game", "doublepatti")
//                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra("open_av", is_open)
//                        );
//                    }
//                });
//
//                dialog.show();
//            }
//        });


        title.setText(market);
        timing.setText(getIntent().getStringExtra("timing"));

        findViewById(R.id.back).setOnClickListener(v -> finish());

        single.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) { // "OPEN" is clicked
                        if (is_open.equals("1")) {
                            number.clear();
                            single();
                            startActivity(new Intent(games.this, single_bet.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "single")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }

                    } else if (which == 1) { // "CLOSE" is clicked

                        number.clear();
                        single();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "single")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    }
                }
            });
            builder.show();
        });

        oddEven.setOnClickListener(v -> {
            if (is_close.equals("1")) {
                number.clear();
                single();
                startActivity(new Intent(games.this, OddEven.class)
                        .putExtra("market", market)
                        .putExtra("game", "single")
                        .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("open_av", is_open)
                );
            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });


        redBracket.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                number.clear();
                jodi();
                startActivity(new Intent(games.this, RedBracket.class)
                        .putExtra("market", market)
                        .putExtra("game", "jodi")
                        .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("open_av", is_open)
                );
            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });

        jodi.setOnClickListener(v -> {

            if (is_open.equals("1")) {
                number.clear();
                jodi();
                startActivity(new Intent(games.this, single_bet.class)
                        .putExtra("market", market)
                        .putExtra("game", "jodi")
                        .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("open_av", is_open)
                );
            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });


        singlepatti.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) { // "OPEN" is clicked
                        if (is_open.equals("1")) {
                            number.clear();
                            singlepatti();
                            startActivity(new Intent(games.this, single_bet.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "singlepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }

                    } else if (which == 1) { // "CLOSE" is clicked
                        number.clear();
                        singlepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "singlepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    }
                }
            });
            builder.show();
        });


        spMotor.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if (is_open.equals("1")) {
                            number.clear();
                            singlepatti();
                            startActivity(new Intent(games.this, SpMotor.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "singlepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                    } else if (which == 1) { // "CLOSE" is clicked
                        // Handle CL

                        number.clear();
                        singlepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "singlepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    }
                }
            });
            builder.show();
        });


        dpMotor.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if (is_open.equals("1")) {
                            number.clear();
                            doublepatti();
                            startActivity(new Intent(games.this, SpMotor.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "doublepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }

                    } else if (which == 1) { // "CLOSE" is clicked
                        // Handle CL
                        number.clear();
                        doublepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "doublepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    } else {
                        new AlertDialog.Builder(games.this)
                                .setTitle("Market Close")
                                .setMessage("This game is already closed for this market")
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    }
                }
            });
            builder.show();
        });

        spdptp.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if (is_open.equals("1")) {
                            number.clear();
                            singlepatti();
                            startActivity(new Intent(games.this, SpDpTp.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "singlepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                    } else if (which == 1) { // "CLOSE" is clicked
                        // Handle CL
                        number.clear();
                        singlepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "singlepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    } else {
                        new AlertDialog.Builder(games.this)
                                .setTitle("Market Close")
                                .setMessage("This game is already closed for this market")
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    }
                }
            });
            builder.show();
        });

        doublepatti.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if (is_open.equals("1")) {
                            number.clear();
                            doublepatti();
                            startActivity(new Intent(games.this, single_bet.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "doublepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }

                    } else if (which == 1) { // "CLOSE" is clicked
                        // Handle CL
                        number.clear();
                        doublepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "doublepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    } else {
                        new AlertDialog.Builder(games.this)
                                .setTitle("Market Close")
                                .setMessage("This game is already closed for this market")
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    }
                }
            });
            builder.show();

        });

        tripepatti.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(games.this);
            builder.setTitle("Select Game Type");
            builder.setItems(new CharSequence[]{"OPEN", "CLOSE"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        if (is_open.equals("1")) {
                            number.clear();
                            triplepatti();
                            startActivity(new Intent(games.this, single_bet.class)
                                    .putExtra("market", market)
                                    .putExtra("game", "triplepatti")
                                    .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .putExtra("open_av", "1")
                            );
                        } else {
                            new AlertDialog.Builder(games.this)
                                    .setTitle("Market Close")
                                    .setMessage("This game is already closed for this market")
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                    } else if (which == 1) { // "CLOSE" is clicked
                        // Handle CL
                        number.clear();
                        triplepatti();
                        startActivity(new Intent(games.this, single_bet.class)
                                .putExtra("market", market)
                                .putExtra("game", "triplepatti")
                                .putExtra("list", number).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putExtra("open_av", "0")
                        );
                    } else {
                        new AlertDialog.Builder(games.this)
                                .setTitle("Market Close")
                                .setMessage("This game is already closed for this market")
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                    }
                }
            });
            builder.show();

        });


        halfsangam.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                startActivity(new Intent(games.this, halfsangam.class)
                        .putExtra("market", market)
                        .putExtra("game", "halfsangam")
                        .putExtra("list", number)
                        .putExtra("open_av", is_open)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );

            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }

        });

        fullsangam.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                startActivity(new Intent(games.this, fullsangam.class)
                        .putExtra("market", market)
                        .putExtra("game", "fullsangam")
                        .putExtra("list", number)
                        .putExtra("open_av", is_open)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );

            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }

        });


//        cyclePanna.setOnClickListener(v -> {
//            if (is_open.equals("1")) {
//                number.clear();
//                Cyclepanna();
//                startActivity(new Intent(games.this, CyclePanna.class)
//                        .putExtra("market", market)
//                        .putExtra("game", "Cycle Patti")
//                        .putExtra("list", number)
//                        .putExtra("open_av", is_open)
//                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                );
//
//
//            } else {
//                new AlertDialog.Builder(games.this)
//                        .setTitle("Market Close")
//                        .setMessage("This game is already closed for this market")
//                        .setNegativeButton(android.R.string.no, null)
//                        .show();

//                number.clear();
//                Cyclepanna();
//                startActivity(new Intent(games.this, CyclePanna.class)
//                        .putExtra("market", market)
//                        .putExtra("game", "Cycle Patti")
//                        .putExtra("list", number)
//                        .putExtra("open_av", "0")
//                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                );
//            }
//
//        });


        familyJodi.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                number.clear();
                FamillyJodi();
                startActivity(new Intent(games.this, FamilyJodi.class)
                        .putExtra("market", market)
                        .putExtra("game", "Family Jodi")
                        .putExtra("list", number)
                        .putExtra("open_av", is_open)
                );


            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }

        });


        redFamilyJodi.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                number.clear();
                RedFamilyJodi();
                startActivity(new Intent(games.this, RedFamilyJodi.class)
                        .putExtra("market", market)
                        .putExtra("game", "Red Family Jodi")
                        .putExtra("list", number)
                        .putExtra("open_av", is_open)
                );


            } else {
                new AlertDialog.Builder(games.this)
                        .setTitle("Market Close")
                        .setMessage("This game is already closed for this market")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }

        });

        familypannel.setOnClickListener(v -> {
            if (is_open.equals("1")) {
                number.clear();
                FamilyPanna();
                startActivity(new Intent(games.this, FamilyPannel.class)
                        .putExtra("market", market)
                        .putExtra("game", "Family Panna")
                        .putExtra("list", number)
                        .putExtra("open_av", is_open)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );


            } else {
//                new AlertDialog.Builder(games.this)
//                        .setTitle("Market Close")
//                        .setMessage("This game is already closed for this market")
//                        .setNegativeButton(android.R.string.no, null)
//                        .show();

                number.clear();
                FamilyPanna();
                startActivity(new Intent(games.this, FamilyPannel.class)
                        .putExtra("market", market)
                        .putExtra("game", "Family Panna")
                        .putExtra("list", number)
                        .putExtra("open_av", "0")
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                );
            }

        });


    }

//    private void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        // Set the dialog title
//        builder.setTitle("Select Game Type");
//
//        // Set the custom layout for the dialog
//        View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
//        builder.setView(dialogView);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.openclose_dialog_box, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        // Set the onClickListener for the "OPEN" option
        TextView openTextView = dialogView.findViewById(R.id.openTextView);
        openTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code to handle "OPEN" option click here
                // For example, you can perform some action or dismiss the dialog

//                dialog.dismiss();

//                startActivity(new Intent(games.this, single_bet.class)
//                        .putExtra("market", market)
//                        .putExtra("game", "Red Family Jodi")
//                        .putExtra("list", number)
//                        .putExtra("open_av", is_open)
//                );
            }
        });

        // Set the onClickListener for the "CLOSE" option
        TextView closeTextView = dialogView.findViewById(R.id.closeTextView);
        closeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code to handle "CLOSE" option click here
                // For example, you can perform some action or dismiss the dialog
//                dialog.dismiss();

//                startActivity(new Intent(games.this, single_bet.class)
//                        .putExtra("market", market)
//                        .putExtra("game", "Red Family Jodi")
//                        .putExtra("list", number)
//                        .putExtra("open_av", is_open)
//                );
            }

        });

        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                finish();
            }
        });

        dialog.show();
    }


    public void single() {
        number.add("0");
        number.add("1");
        number.add("2");
        number.add("3");
        number.add("4");
        number.add("5");
        number.add("6");
        number.add("7");
        number.add("8");
        number.add("9");
    }

    public void doublepatti() {

        number.add("100");
        number.add("110");
        number.add("112");
        number.add("113");
        number.add("114");
        number.add("115");
        number.add("116");
        number.add("117");
        number.add("118");
        number.add("119");
        number.add("122");
        number.add("133");
        number.add("144");
        number.add("155");
        number.add("166");
        number.add("177");
        number.add("188");
        number.add("199");
        number.add("200");
        number.add("220");
        number.add("223");
        number.add("224");
        number.add("225");
        number.add("226");
        number.add("266");
        number.add("227");
        number.add("228");
        number.add("229");
        number.add("233");
        number.add("244");
        number.add("255");
        number.add("277");
        number.add("288");
        number.add("299");
        number.add("300");
        number.add("330");
        number.add("334");
        number.add("335");
        number.add("336");
        number.add("337");
        number.add("338");
        number.add("339");
        number.add("344");
        number.add("355");
        number.add("366");
        number.add("377");
        number.add("388");
        number.add("399");
        number.add("400");
        number.add("440");
        number.add("445");
        number.add("446");
        number.add("447");
        number.add("448");
        number.add("449");
        number.add("455");
        number.add("466");
        number.add("477");
        number.add("488");
        number.add("499");
        number.add("500");
        number.add("550");
        number.add("556");
        number.add("557");
        number.add("558");
        number.add("559");
        number.add("566");
        number.add("577");
        number.add("588");
        number.add("599");
        number.add("600");
        number.add("660");
        number.add("667");
        number.add("668");
        number.add("669");
        number.add("677");
        number.add("688");
        number.add("699");
        number.add("700");
        number.add("770");
        number.add("778");
        number.add("779");
        number.add("788");
        number.add("799");
        number.add("800");
        number.add("880");
        number.add("889");
        number.add("899");
        number.add("900");
        number.add("990");

    }


    public void singlepatti() {

        number.add("120");
        number.add("123");
        number.add("124");
        number.add("125");
        number.add("126");
        number.add("127");
        number.add("128");
        number.add("129");
        number.add("130");
        number.add("134");
        number.add("135");
        number.add("136");
        number.add("137");
        number.add("138");
        number.add("139");
        number.add("140");
        number.add("145");
        number.add("146");
        number.add("147");
        number.add("148");

        number.add("149");
        number.add("150");
        number.add("156");
        number.add("157");
        number.add("158");
        number.add("159");
        number.add("160");
        number.add("167");
        number.add("168");
        number.add("169");
        number.add("170");
        number.add("178");
        number.add("179");
        number.add("180");
        number.add("189");
        number.add("190");
        number.add("230");
        number.add("234");
        number.add("235");
        number.add("236");
        number.add("237");
        number.add("238");
        number.add("239");
        number.add("240");
        number.add("245");
        number.add("246");
        number.add("247");
        number.add("248");
        number.add("249");
        number.add("250");
        number.add("256");
        number.add("257");
        number.add("258");
        number.add("259");
        number.add("260");
        number.add("267");
        number.add("268");
        number.add("269");
        number.add("270");
        number.add("278");
        number.add("279");
        number.add("280");
        number.add("289");
        number.add("290");
        number.add("340");
        number.add("345");
        number.add("346");
        number.add("347");
        number.add("348");
        number.add("349");
        number.add("350");
        number.add("356");
        number.add("357");
        number.add("358");
        number.add("359");
        number.add("360");
        number.add("367");
        number.add("368");
        number.add("369");
        number.add("370");
        number.add("378");
        number.add("379");
        number.add("380");
        number.add("389");
        number.add("390");
        number.add("450");
        number.add("456");
        number.add("457");
        number.add("458");
        number.add("459");
        number.add("460");
        number.add("467");
        number.add("468");
        number.add("469");
        number.add("470");
        number.add("478");
        number.add("479");
        number.add("480");
        number.add("489");
        number.add("490");
        number.add("560");
        number.add("567");
        number.add("568");
        number.add("569");
        number.add("570");
        number.add("578");
        number.add("579");
        number.add("580");
        number.add("589");
        number.add("590");
        number.add("670");
        number.add("678");
        number.add("679");
        number.add("680");
        number.add("689");
        number.add("789");
        number.add("690");
        number.add("780");
        number.add("790");
        number.add("890");
    }

    public void triplepatti() {
        number.add("000");
        number.add("111");
        number.add("222");
        number.add("333");
        number.add("444");
        number.add("555");
        number.add("666");
        number.add("777");
        number.add("888");
        number.add("999");
    }

    public void Cyclepanna() {
        number.add("00");
        number.add("10");
        number.add("11");
        number.add("12");
        number.add("13");
        number.add("14");
        number.add("15");
        number.add("16");
        number.add("17");
        number.add("18");
        number.add("19");
        number.add("20");
        number.add("22");
        number.add("23");
        number.add("24");
        number.add("25");
        number.add("26");
        number.add("27");
        number.add("28");
        number.add("29");
        number.add("30");
        number.add("33");
        number.add("34");
        number.add("35");
        number.add("36");
        number.add("37");
        number.add("38");
        number.add("39");
        number.add("40");
        number.add("44");
        number.add("45");
        number.add("46");
        number.add("47");
        number.add("48");
        number.add("49");
        number.add("50");
        number.add("55");
        number.add("56");
        number.add("57");
        number.add("58");
        number.add("59");
        number.add("60");
        number.add("66");
        number.add("67");
        number.add("68");
        number.add("69");
        number.add("70");
        number.add("77");
        number.add("78");
        number.add("79");
        number.add("80");
        number.add("88");
        number.add("89");
        number.add("90");
        number.add("99");
    }

    public void FamillyJodi() {
        number.add("12");
        number.add("13");
        number.add("14");
        number.add("15");
        number.add("23");
        number.add("24");
        number.add("25");
        number.add("34");
        number.add("35");
        number.add("45");
    }

    public void RedFamilyJodi() {
        number.add("00");
        number.add("11");
        number.add("22");
        number.add("33");
        number.add("44");
    }

    public void FamilyPanna() {
        number.add("111");
        number.add("112");
        number.add("113");
        number.add("114");
        number.add("115");
        number.add("122");
        number.add("123");
        number.add("124");
        number.add("125");
        number.add("133");
        number.add("134");
        number.add("135");
        number.add("144");
        number.add("145");
        number.add("155");

        number.add("222");
        number.add("223");
        number.add("224");
        number.add("225");
        number.add("233");
        number.add("234");
        number.add("235");
        number.add("244");
        number.add("245");
        number.add("255");
        number.add("333");
        number.add("334");
        number.add("335");
        number.add("344");
        number.add("345");
        number.add("355");
        number.add("444");
        number.add("445");
        number.add("455");
        number.add("555");
    }


    public void jodi() {
        for (int i = 0; i < 100; i++) {
            String temp = String.format("%02d", i);
            number.add(temp);
        }
    }

//    public void single_bet() {
//        Intent intent = new Intent(this, single_bet.class);
//        intent.putExtra("jodiii", "numb"); // Pass any necessary data
//        startActivity(intent);
//    }


    private void initViews() {
        single = findViewById(R.id.single);
        jodi = findViewById(R.id.jodi);
        singlepatti = findViewById(R.id.singlepatti);
        doublepatti = findViewById(R.id.doublepatti);
        tripepatti = findViewById(R.id.tripepatti);
        halfsangam = findViewById(R.id.halfsangam);
        fullsangam = findViewById(R.id.fullsangam);
        timing = findViewById(R.id.timing);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        oddEven = findViewById(R.id.odd_even);
        redBracket = findViewById(R.id.red_bracket);
        spdptp = findViewById(R.id.spdptp);
        spMotor = findViewById(R.id.sp_motor);
        dpMotor = findViewById(R.id.dp_motor);
        cyclePanna = findViewById(R.id.cyclePanna);
        familyJodi = findViewById(R.id.familyJodi);
        redFamilyJodi = findViewById(R.id.redFamilyJodi);
        familypannel = findViewById(R.id.familypannel);

    }
}