package com.yahoo.android_todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EditItemActivity extends Activity {
    private String editingItem = null;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getIntentFromMainActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getIntentFromMainActivity() {
        editingItem = getIntent().getStringExtra("editing_item");
        position = getIntent().getIntExtra("position", -1);

        EditText editText = (EditText) findViewById(R.id.editText);
        if (position >= 0) {
            editText.setText(editingItem);
            editText.setSelection(editText.getText().length());
        } else {
            Toast.makeText(this, "Errno", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSave(View v) {
        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = new Intent();
        intent.putExtra("editing_item", editText.getText().toString());
        intent.putExtra("position", position);

        setResult(RESULT_OK, intent);

        finish();
    }
}
