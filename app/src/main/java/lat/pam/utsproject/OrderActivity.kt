package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val btnBackToList = findViewById<ImageButton>(R.id.btnBackToList)
        btnBackToList.setOnClickListener {
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnOrder = findViewById<Button>(R.id.btnOrder)
        btnOrder.setOnClickListener {
            val foodName = findViewById<EditText>(R.id.etFoodName).text.toString().trim()
            val servings = findViewById<EditText>(R.id.etServings).text.toString().trim()
            val orderingName = findViewById<EditText>(R.id.etName).text.toString().trim()
            val notes = findViewById<EditText>(R.id.etNotes).text.toString().trim()

            if (foodName.isEmpty()) {
                findViewById<EditText>(R.id.etFoodName).error = "Nama makanan harus diisi"
                return@setOnClickListener
            }

            if (servings.isEmpty()) {
                findViewById<EditText>(R.id.etServings).error = "Jumlah porsi harus diisi"
                return@setOnClickListener
            }

            if (orderingName.isEmpty()) {
                findViewById<EditText>(R.id.etName).error = "Nama pemesan harus diisi"
                return@setOnClickListener
            }

            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("FOOD_NAME", foodName)
            intent.putExtra("SERVINGS", servings)
            intent.putExtra("ORDERING_NAME", orderingName)
            intent.putExtra("NOTES", notes)

            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
