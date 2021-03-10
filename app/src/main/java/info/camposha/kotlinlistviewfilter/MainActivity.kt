package info.camposha.kotlinlistviewfilter

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   private var adapter: ArrayAdapter<CosmicBody>? = null
    var categories = arrayOf("All", "Planets", "Stars", "Galaxies")

    /*
    Initialize ListView and Spinner, set their adapters and listen to spinner itemSelection events
    */
    private fun initializeViews() {
        mySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        myListView.adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            cosmicBodies
        )
        //myListView.adapter=adapter


        //spinner selection events
        mySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                itemID: Long
            ) {
                if (position >= 0 && position < categories.size) {
                    getSelectedCategoryData(position)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Selected Category Does not Exist!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    /*
   Populate an arraylist that will act as our data source.
    */
    private val cosmicBodies: ArrayList<CosmicBody>
        get() {
            val data = ArrayList<CosmicBody>()
            data.add(CosmicBody("Mercury", 1))
            data.add(CosmicBody("UY Scuti", 1))
            data.add(CosmicBody("Andromeda", 3))
            data.add(CosmicBody("VV Cephei A", 2))
            data.add(CosmicBody("IC 1011", 3))
            data.add(CosmicBody("Sun", 2))
            data.add(CosmicBody("Aldebaran", 2))
            data.add(CosmicBody("Venus", 1))
            data.add(CosmicBody("Malin 1", 3))
            data.add(CosmicBody("Rigel", 2))
            data.add(CosmicBody("Earth", 1))
            data.add(CosmicBody("Whirlpool", 3))
            data.add(CosmicBody("VY Canis Majoris", 2))
            data.add(CosmicBody("Saturn", 1))
            data.add(CosmicBody("Sombrero", 3))
            data.add(CosmicBody("Betelgeuse", 2))
            data.add(CosmicBody("Uranus", 1))
            data.add(CosmicBody("Virgo Stellar Stream", 3))
            data.add(CosmicBody("Epsillon Canis Majoris", 2))
            data.add(CosmicBody("Jupiter", 1))
            data.add(CosmicBody("VY Canis Majos", 2))
            data.add(CosmicBody("Triangulum", 3))
            data.add(CosmicBody("Cartwheel", 3))
            data.add(CosmicBody("Antares", 2))
            data.add(CosmicBody("Mayall's Object", 3))
            data.add(CosmicBody("Proxima Centauri", 2))
            data.add(CosmicBody("Black Eye", 3))
            data.add(CosmicBody("Mars", 1))
            data.add(CosmicBody("Sirius", 2))
            data.add(CosmicBody("Centaurus A", 3))
            data.add(CosmicBody("Pinwheel", 3))
            data.add(CosmicBody("Small Magellonic Cloud", 3))
            data.add(CosmicBody("Uranus", 1))
            data.add(CosmicBody("Alpha Centauri", 2))
            data.add(CosmicBody("Large Magellonic Cloud", 3))
            return data
        }

    /*
   Get the selected category's cosmic bodies and bind to ListView
    */
    private fun getSelectedCategoryData(categoryID: Int) {
        //arraylist to hold selected cosmic bodies
        val data = ArrayList<CosmicBody>()
        adapter = if (categoryID == 0) {
            ArrayAdapter(
                this, android.R.layout.simple_list_item_1,
                cosmicBodies
            )

        } else {
            //filter by id
            for (cosmicBody in cosmicBodies) {
                if (cosmicBody.categoryId == categoryID) {
                    data.add(cosmicBody)
                }
            }
            //instatiate adapter a
            ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        }
        //set the adapter to GridView
        myListView!!.adapter = adapter
    }

    /*
    when activity is created, setContentView then initializeViews.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
    }
}

/*
Data Object class to represent a single Cosmic body. Class has default access modifier
 */
class CosmicBody(private val name: String, val categoryId: Int) {

    override fun toString(): String {
        return name
    }
}