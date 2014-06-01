package com.kphoen.muchtip.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.kphoen.muchtip.R;
import com.kphoen.muchtip.computer.TipComputer;

public class MainActivity extends Activity {
	protected TipComputer computer = new TipComputer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupListeners();
	}
	
	protected void setupListeners() {
		EditText amountField = (EditText) findViewById(R.id.amount);
		RatingBar serviceRating = (RatingBar) findViewById(R.id.service_rating);
		final MainActivity that = this;
		
		amountField.addTextChangedListener(new TextWatcher() {	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				that.updateTipAmount();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		serviceRating.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {	
				that.updateTipAmount();
			}
		});
	}

	protected float getBillAmount() {
		EditText amountField = (EditText) findViewById(R.id.amount);
		String billAmount = amountField.getText().toString();
		
		if (billAmount.length() == 0) {
			return 0f;
		}

		return Float.valueOf(billAmount);
	}

	protected int getServiceRating() {
		RatingBar serviceRating = (RatingBar) findViewById(R.id.service_rating);

		return (int) serviceRating.getRating();
	}
	
	protected float getTipAmount() {
		return computer.tipFor(getBillAmount(), getServiceRating());
	}
	
	protected void updateTipAmount() {
		TextView tipLabel = (TextView) findViewById(R.id.tip_content); 
		tipLabel.setText(String.format("%.2f$", getTipAmount()));
	}
}
