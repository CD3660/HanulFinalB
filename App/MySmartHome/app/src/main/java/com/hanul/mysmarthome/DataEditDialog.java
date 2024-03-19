package com.hanul.mysmarthome;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hanul.mysmarthome.databinding.DialogDataEditBinding;

public class DataEditDialog extends Dialog {

    DialogDataEditBinding binding;

    /** sub_title, edit_data 값 없는 경우 null로 전송 */
    public DataEditDialog(@NonNull Context context, String title, String sub_title, String edit_data) {
        super(context);
        binding = DialogDataEditBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());
        binding.title.setText(title);
        if (sub_title != null) {
            binding.subtitle.setText(sub_title);
        } else {
            binding.subtitle.setVisibility(View.GONE);
        }
        if(edit_data != null){
            binding.editData.setText(edit_data);
        }
        binding.tvCancel.setOnClickListener(v -> {
            dismiss();
        });
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        setCancelable(true);

    }

    @Override
    public void onBackPressed() {
        dismiss();
    }
    public void setSubmitListener(View.OnClickListener listener){
        binding.tvSubmit.setOnClickListener(listener);
    }

    public DialogDataEditBinding getBinding(){return binding;}
}
