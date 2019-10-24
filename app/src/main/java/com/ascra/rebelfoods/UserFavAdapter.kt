package com.ascra.rebelfoods

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class UserFavAdapter: RecyclerView.Adapter<UserFavAdapter.UserHolder>() {

    private var userList: ArrayList<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_user_favourite, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = userList!![position]

        val name = user.name
        val company_name = user.company
        val email = user.email
        val address = user.address
        val lat = user.latitude
        val lng = user.longitude

        holder.name!!.text = name
        holder.email!!.text = email
        holder.location!!.text = address
        holder.companyName!!.text = company_name

        holder.rlRoot!!.setOnClickListener { v ->
            val placeIntent = Intent(v.context, AddressActivity::class.java)
            placeIntent.putExtra(ApplicationConstant.LATITUDE, lat!!.toDouble())
            placeIntent.putExtra(ApplicationConstant.LONGITUDE, lng!!.toDouble())
            placeIntent.putExtra(ApplicationConstant.PLACE, address)
            v.context.startActivity(placeIntent)
        }
    }

    override fun getItemCount(): Int {
        return if (userList == null) 0 else userList!!.size
    }

    fun setUserListToAdapter(userList: List<User>) {
        this.userList = ArrayList()
        this.userList!!.clear()
        this.userList!!.addAll(userList)
        notifyDataSetChanged()
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView? = null
        var location: TextView? = null
        var email: TextView? = null
        var companyName: TextView? = null
        var rlRoot: RelativeLayout? = null

        init {
            rlRoot = itemView.findViewById(R.id.rl_root)
            companyName = itemView.findViewById(R.id.company_name)
            email = itemView.findViewById(R.id.email)
            location = itemView.findViewById(R.id.location)
            name = itemView.findViewById(R.id.name)
        }
    }
}
