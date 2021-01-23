package com.example.pesankopi


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat


/**
 * This app displays an order form to order coffee.
 */
class MainActivity : AppCompatActivity() {
    var quantity = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun increment(view: View?) { //perintah tombol tambah
        if (quantity == 100) {
            Toast.makeText(this, "pesanan maximal 100", Toast.LENGTH_SHORT).show()
            return
        }
        quantity = quantity + 1
        display(quantity)
    }

    fun decrement(view: View?) { //perintah tombol tambah
        if (quantity == 1) {
            Toast.makeText(this, "pesanan minimal 1", Toast.LENGTH_SHORT).show()
            return
        }
        quantity = quantity - 1
        display(quantity)
    }

    fun Submitorder(view: View?) {
        val nameEditText = findViewById<View>(R.id.edt_name) as EditText
        val name = nameEditText.text.toString()
        Log.v("MainActivity", "Nama:$name")
        val esspressoCheckBox = findViewById<View>(R.id.espresso) as CheckBox
        val hasespresso = esspressoCheckBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has espresso:$hasespresso")
        val macchiatoCheckBox = findViewById<View>(R.id.macchiato) as CheckBox
        val hasmacchiato = macchiatoCheckBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has macchiato:$hasmacchiato")
        val latteCheckBox = findViewById<View>(R.id.latte) as CheckBox
        val haslatte = latteCheckBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has latte:$haslatte")
        val cappucinoChekBox = findViewById<View>(R.id.cappucino) as CheckBox
        val hascappucino = cappucinoChekBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has cappucino:$hascappucino")
        val affogatoChekBox = findViewById<View>(R.id.affogato) as CheckBox
        val hasaffogato = affogatoChekBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has affogato:$hasaffogato")
        val mochaCheckBox = findViewById<View>(R.id.mocha) as CheckBox
        val hasmocha = mochaCheckBox.isChecked //mengidentifikasi check
        Log.v("MainActivity", "has mocha:$hasmocha")
        val price = calculateprice(hasespresso, hasmacchiato, haslatte, hascappucino, hasaffogato, hasmocha) //memanggil method jumlah harga
        val pricemessage = createOrderSummary(price, name, hasespresso, hasmacchiato, haslatte, hascappucino, hasaffogato, hasmocha)
        displayMessage(pricemessage)
    }

    private fun calculateprice(
        addespresso: Boolean,
        addmacchiato: Boolean,
        addlatte: Boolean,
        addcappucino: Boolean,
        addaffogato: Boolean,
        addmocha: Boolean
    ): Int { //jumlah pesanan * harga
        var harga = 0
        if (addespresso) {
            harga = harga + 15000 //harga tambahan toping
        }
        if (addmacchiato) {
            harga = harga + 25000
        }
        if (addlatte) {
            harga = harga + 20000
        }
        if (addcappucino) {
            harga = harga + 20000
        }
        if (addaffogato) {
            harga = harga + 21000
        }
        if (addmocha) {
            harga = harga + 18000
        }
        return quantity * harga
    }

    private fun createOrderSummary(
        price: Int,
        name: String,
        addEspresso: Boolean,
        addMacchiato: Boolean,
        addLatte: Boolean,
        addCappucino: Boolean,
        addAffogato: Boolean,
        addMocha: Boolean
    ): String { //hasil pemesanan
        var pricemessage = " Nama = $name"
        pricemessage += "\n Tambahkan Espresso =$addEspresso"
        pricemessage += "\n Tambahkan Macchiato =$addMacchiato"
        pricemessage += "\n Tambahkan Latte =$addLatte"
        pricemessage += "\n Tambahkan Cappucino =$addCappucino"
        pricemessage += "\n Tambahkan Affogato =$addAffogato"
        pricemessage += "\n Tambahkan Mochacino =$addMocha"
        pricemessage += "\n Jumlah Pemesanan =$quantity"
        pricemessage += "\n Total = Rp $price"
        pricemessage += "\n Terima kasih"
        return pricemessage
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private fun displayMessage(message: String) {
        val priceTextView = findViewById<View>(R.id.price_textview) as TextView
        priceTextView.text = message
    }

    private fun display(number: Int) {
        val quantityTextView = findViewById<View>(R.id.quantity_textview) as TextView
        quantityTextView.text = "" + number
    }

    private fun displayPrice(number: Int) {
        val priceTextView = findViewById<View>(R.id.price_textview) as TextView
        priceTextView.text = NumberFormat.getCurrencyInstance().format(number.toLong())
    }
}