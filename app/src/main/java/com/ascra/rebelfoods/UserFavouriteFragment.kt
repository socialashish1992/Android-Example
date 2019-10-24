package com.ascra.rebelfoods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UserFavouriteFragment : Fragment() {

    var rvUser: RecyclerView? = null
    var tvNoData: TextView? = null

    private var userAdapter: UserFavAdapter? = null

    private val inputObserver: Observer<List<User>> = object : Observer<List<User>> {
        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: List<User>) {

            if (t.isNotEmpty()) {
                userAdapter!!.setUserListToAdapter(t)
                rvUser!!.visibility = View.VISIBLE
                tvNoData!!.visibility = View.GONE
            } else {
                rvUser!!.visibility = View.GONE
                tvNoData!!.visibility = View.VISIBLE
            }
        }

        override fun onError(e: Throwable) {

        }

        override fun onComplete() {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNoData = view.findViewById(R.id.tv_no_data)
        rvUser = view.findViewById(R.id.rv_user)

        userAdapter = UserFavAdapter()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rvUser!!.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(rvUser!!.context, linearLayoutManager.orientation)
        rvUser!!.addItemDecoration(dividerItemDecoration)
        rvUser!!.adapter = userAdapter

        RxBus.instance().listen().subscribe(inputObserver)
    }

    companion object {

        fun newInstance(): UserFavouriteFragment {
            return UserFavouriteFragment()
        }
    }
}
