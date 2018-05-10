package com.example.sam.dinnerdecider
/*    packages    */
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    /*main food array*/
    var foodlist = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*TEXT FIELD*/
        addFoodTxt.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2!!.getAction() == KeyEvent.ACTION_DOWN) {
                    when(p1)
                    {
                        KeyEvent . KEYCODE_ENTER  ->
                            handleAddItems()
                    }
                }
                return false;
            }

        })

        /*Decide button process*/
        decideBtn.setOnClickListener {
            val random = Random()

            if (foodlist.size == 0) {
                Toast.makeText(this, "Please add some Food First !", Toast.LENGTH_SHORT).show()
            } else {
                selectedFoodTxt.text = foodlist[random.nextInt(foodlist.size)]
            }
        }

        /*Add Food Button*/
        addFoodBtn.setOnClickListener {
            handleAddItems()
        }

        /*Clear foodlist array*/
        clearBtn.setOnClickListener{
            foodlist.clear()
            selectedFoodTxt.text = "Click on Decide!"
            if(foodlist.size == 0)
                Toast.makeText(this, "Food list is already cleared !", Toast.LENGTH_SHORT).show()

        }

        /*Image button for new page called about.kt*/
        imageBtn.setOnClickListener{
            val intent = Intent(this@MainActivity,About::class.java)
            startActivity(intent)
        }

        /*View food button */
        viewFoodBtn.setOnClickListener {
            if (foodlist.size == 0){
                Toast.makeText(this, "Please add some Food First !", Toast.LENGTH_SHORT).show()
            }
            else {
                displayAlert().show()
            }
        }
    }

    /* processing of AddItem into array*/
    fun handleAddItems (){
        val newFood = addFoodTxt.text.toString()
        if (!newFood.isEmpty() && !foodlist.contains(newFood)) {
            foodlist.add(newFood)
        }
        addFoodTxt.text.clear()
    }

    /*displaying alert box for already added food */
    fun displayAlert(): AlertDialog.Builder {
        val alert = AlertDialog.Builder(this)

        with (alert){
            var finalItem =""
            setTitle("Already added Foods.")
            for(i in foodlist.indices) {
                finalItem = "\n" + finalItem + foodlist[i] + "\n"
            }
            setMessage(finalItem)
        }
        return alert
    }
}
