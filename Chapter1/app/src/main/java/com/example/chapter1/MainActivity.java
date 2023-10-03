package com.example.chapter1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.chapter1.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        final EditText textInput = binding.textInput;

        String msg1 = "Text length is more than 7 characters";
        String msg2 = "<7";

        RxTextView.textChanges(textInput)
                .map(text -> text.length() >= 7 ? msg1 : msg2)
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateSearchResults);
    }

    private void updateSearchResults(CharSequence textInput) {
        //binding.textView.setText(search);
        binding.textView.setText(textInput.toString());
    }

}