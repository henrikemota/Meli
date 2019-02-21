package br.com.hkmobi.mercadolivre.view.product

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import br.com.hkmobi.mercadolivre.R
import br.com.hkmobi.mercadolivre.viewmodel.product.ProductViewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.hkmobi.mercadolivre.data.model.Product
import kotlinx.android.synthetic.main.activity_products.*
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.content_list_empty.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProductsActivity : AppCompatActivity() {

    val productViewModel: ProductViewModel by viewModel()

    private lateinit var searchView: SearchView

    private lateinit var products: ArrayList<Product>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var query = ""
    private var containsError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setAdapter()
        setValues()
        setListener()
    }

    private fun setAdapter(){
        products = ArrayList()
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, (recyclerView.layoutManager as LinearLayoutManager).orientation))
    }

    private fun setListener(){
        swipeRefreshLayout.setOnRefreshListener {
            getProducts()
        }

        animationSearch.setOnClickListener { searchView.isIconified = false }
    }

    @SuppressLint("StringFormatMatches")
    private fun setValues() {
        productViewModel.error().observe(this, Observer {
            animationError.visibility = View.VISIBLE
            animationSearch.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
            containsError = true
        })

        productViewModel.statusProgress().observe(this, Observer { status ->
            progressBar.visibility = status
            animationError.visibility = View.GONE
            animationSearch.visibility = View.GONE
        })

        productViewModel.getProducts().observe(this, Observer { products ->
            this.products.clear()
            animationSearch.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
            containsError = false
            if(products.isEmpty()){
                setProductsEmpty()
            }else{
                setProducts(products)
            }
        })

    }

    private fun setProducts(products: ArrayList<Product>){
        this.products.addAll(products)
        productAdapter.notifyDataSetChanged()
    }

    private fun setProductsEmpty(){
        productAdapter.notifyDataSetChanged()
        if(!containsError) contentEmpty.visibility = View.VISIBLE
        msgEmpty.text = getString(R.string.msg_search_empty, query)
    }

    private fun getProducts(){
        if(query.isEmpty()) {
            products.clear()
            productAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
            animationSearch.visibility = View.VISIBLE
            contentEmpty.visibility = View.GONE
            animationError.visibility = View.GONE
        } else {
            animationSearch.visibility = View.GONE
            contentEmpty.visibility = View.GONE
            animationError.visibility = View.GONE
            productViewModel.products(query)
        }
    }

    @SuppressLint("PrivateResource")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu!!.findItem(R.id.action_search_list_activity).actionView as SearchView
        searchView.queryHint = getString(R.string.search_menu_title)

        productViewModel.getQuery().observe(this, Observer { query ->
            this.query = query
            searchView.setQuery(query, false)
            if(!query.isNullOrEmpty()) searchView.isIconified = false
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                productViewModel.setQuery(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                this@ProductsActivity.query = query
                getProducts()
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    fun startActivity(context: Context){
        val intent = Intent(context, ProductsActivity::class.java)
        context.startActivity(intent)
    }
}
