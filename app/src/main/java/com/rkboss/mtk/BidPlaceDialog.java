package com.rkboss.mtk;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BidPlaceDialog extends DialogFragment {

    ImageView betimage,okbtn;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        // Create the dialog
        Dialog dialog = new Dialog(requireContext(),R.style.FullScreenDialog);

        // Adjust the dialog's window attributes
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.semi_white)));
        }


        dialog.setContentView(R.layout.bet_dialog_ax);

        betimage = dialog.findViewById(R.id.betimage);
        okbtn = dialog.findViewById(R.id.okbtn);

        dialog.show();

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Intent in = new Intent(requireContext(), games.class);
                startActivity(in); requireContext().startActivity(new Intent(requireContext(),MainActivity.class));

            }
        });

        return dialog;

    }



}


