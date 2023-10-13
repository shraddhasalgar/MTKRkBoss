package com.rkboss.mtk;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {
//    private List<String> numbers;
    private static List<Integer> editTextValues;
    private static List<String> dataList;
    private Context context;
    int editedValue;
    int values;

    int pos;
    private List<Pair<Integer, Integer>> itemDataList; // List to store (position, value) pairs

    public NumberAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
//        this.numbers = numbers;
        itemDataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            itemDataList.add(new Pair<>(i, 0)); // Initialize pairs with position and value
        }
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number, parent, false);
        return new NumberViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        String item = dataList.get(position);
        holder.bind(item);
        final int finalPosition = position;
        holder.editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Log.d("beforetextchanged", "");
                // Unused
            }


            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = charSequence.toString();
                if (!value.isEmpty()) {

                    itemDataList.set(finalPosition, new Pair<>(Integer.parseInt(item), Integer.parseInt(value)));
//                    String positionStr = holder.textViewNumber.getText().toString();
//                    int position = Integer.parseInt(positionStr);
//                    int position = getAdapterPosition();
//                    itemDataList.set(position, new Pair<>(position, Integer.parseInt(value)));
                } else {
                    // Handle empty value if needed
                }

                // Call the method to update values in the activity
                updatevalues();
                calculateAndDisplayTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

                // Unused
            }
        });
    }

    private void calculateAndDisplayTotal() {
        int total = 0;
        for (Pair<Integer, Integer> pair : itemDataList) {
            total += pair.second; // Sum all the values in the itemDataList
        }

        // Notify the MainActivity to update the total
        ((single_bet) context).updateTotal(total);
    }

    private void updatevalues() {
        List<Integer> positions = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (Pair<Integer, Integer> pair : itemDataList) {
            positions.add(pair.first);
            values.add(pair.second);
        }

        // Send the lists to the activity
        ((single_bet) context).updatedValues(positions, values);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static List<Integer> getEditTextValues() {
        return editTextValues;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNumber;
        private EditText editTextNumber;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.text_view_number);
            editTextNumber = itemView.findViewById(R.id.edit_text_number);
        }

        public void bind(String item1) {

            textViewNumber.setText(item1);
            editTextNumber.setText("");
            // Clear previous text from the EditText
        }
    }

}

