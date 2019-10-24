package com.ascra.rebelfoods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserFragment : Fragment() {

    var rvUser: RecyclerView? = null
    var tvNoData: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNoData = view.findViewById(R.id.tv_no_data)
        rvUser = view.findViewById(R.id.rv_user)

        val userAdapter = UserAdapter()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rvUser!!.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(rvUser!!.context, linearLayoutManager.orientation)
        rvUser!!.addItemDecoration(dividerItemDecoration)
        rvUser!!.adapter = userAdapter

        val progressBar = ProgressBar(context)

        if (ApplicationConstant.isNetworkConnected(requireContext())) {

            UserApiClient.userServiceInstance().allUserFromApi.enqueue(object : Callback<ArrayList<UserResponseModel>> {
                override fun onResponse(call: Call<ArrayList<UserResponseModel>>, response: Response<ArrayList<UserResponseModel>>) {
                    val list = response.body()
                    progressBar.visibility = View.GONE

                    if (list != null && list.size > 0) {
                        userAdapter.setUserListToAdapter(list)
                        rvUser!!.visibility = View.VISIBLE
                        tvNoData!!.visibility = View.GONE
                    } else {
                        rvUser!!.visibility = View.GONE
                        tvNoData!!.visibility = View.VISIBLE
                        //Log.d("UserFragment", "User list is blank.")
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserResponseModel>>, t: Throwable) {
                    //Log.d("UserFragment", t.localizedMessage!!)
                }
            })
        } else {
            Toast.makeText(requireContext(), "No internet.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}
