package br.com.hkmobi.mercadolivre.view.detailproduct.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import br.com.hkmobi.mercadolivre.data.model.Picture
import com.facebook.drawee.view.SimpleDraweeView

class ImageAdapter(val context: Context, private val images: ArrayList<Picture>): PagerAdapter(){

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = SimpleDraweeView(context)
        imageView.setImageURI(images[position].secure_url)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as SimpleDraweeView)
    }

}