package com.ascra.rebelfoods

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userList: ArrayList<UserResponseModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = userList!![position]

        val id = user.id
        val name = user.name
        val company_name = user.company!!.name
        val phone = user.phone
        val email = user.email
        val street = user.address!!.street
        val city = user.address!!.city
        val suite = user.address!!.suite
        val zip_code = user.address!!.zipcode
        val lat = user.address!!.geo!!.lat
        val lng = user.address!!.geo!!.lng
        val address = "$street, $city, $suite, $zip_code"

        holder.name!!.text = name
        holder.phone!!.text = phone
        holder.email!!.text = email
        holder.location!!.text = address
        holder.companyName!!.text = company_name

        if (user.isSelected)
            holder.imgFav!!.setImageResource(R.drawable.ic_favorite_pink_24dp)
        else
            holder.imgFav!!.setImageResource(R.drawable.ic_favorite_black_24dp)

        holder.rlRoot!!.setOnClickListener { v ->
            val placeIntent = Intent(v.context, AddressActivity::class.java)
            placeIntent.putExtra(ApplicationConstant.LATITUDE, lat!!.toDouble())
            placeIntent.putExtra(ApplicationConstant.LONGITUDE, lng!!.toDouble())
            placeIntent.putExtra(ApplicationConstant.PLACE, address)
            v.context.startActivity(placeIntent)
        }

        holder.imgFav!!.setOnClickListener { v ->
            user.isSelected = !user.isSelected

            val user1 = User()

            user1.uid = id
            user1.email = email
            user1.name = name
            user1.address = address
            user1.latitude = lat
            user1.longitude = lng
            user1.company = company_name

            if (user.isSelected) {

                holder.imgFav!!.setImageResource(R.drawable.ic_favorite_pink_24dp)

                val mUser = RebelFoodsApplication.getDB().userDao().getUserById(id)

                if (mUser != null)
                    RebelFoodsApplication.getDB().userDao().updateUser(user1)
                else {
                    RebelFoodsApplication.getDB().userDao().insertUser(user1)
                }

                Toast.makeText(v.context,"Item Marked",Toast.LENGTH_SHORT).show()

            } else {
                holder.imgFav!!.setImageResource(R.drawable.ic_favorite_black_24dp)

                RebelFoodsApplication.getDB().userDao().deleteUserBydId(id)

                Toast.makeText(v.context,"Item UnMarked",Toast.LENGTH_SHORT).show()
            }

            RxBus.instance().publish(RebelFoodsApplication.getDB().userDao().allUser)
        }

        RxBus.instance().publish(RebelFoodsApplication.getDB().userDao().allUser)
    }

    override fun getItemCount(): Int {
        return if (userList == null) 0 else userList!!.size
    }

    fun setUserListToAdapter(userList: ArrayList<UserResponseModel>) {
        this.userList = ArrayList()
        this.userList!!.clear()
        this.userList!!.addAll(userList)
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView? = null
        var phone: TextView? = null
        var location: TextView? = null
        var email: TextView? = null
        var companyName: TextView? = null
        var rlRoot: RelativeLayout? = null
        var imgFav: ImageView? = null

        init {
            rlRoot = itemView.findViewById(R.id.rl_root)
            companyName = itemView.findViewById(R.id.company_name)
            email = itemView.findViewById(R.id.email)
            location = itemView.findViewById(R.id.location)
            name = itemView.findViewById(R.id.name)
            phone = itemView.findViewById(R.id.phone)
            imgFav = itemView.findViewById(R.id.img_fav)
        }
    }
}
