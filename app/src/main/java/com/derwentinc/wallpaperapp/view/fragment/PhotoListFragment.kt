package com.derwentinc.wallpaperapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.derwentinc.wallpaperapp.R
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.view.adapter.PhotoAdapter
import com.derwentinc.wallpaperapp.viewmodel.PhotoRepositoryImpl


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PhotoListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PhotoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PhotoListFragment : Fragment(), PhotoClickListener {

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoRepository: PhotoRepositoryImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.photolist_fragment, container, false)

        photoRepository = PhotoRepositoryImpl("Japan")

        val applicationContext = view.context.applicationContext
        photoAdapter = PhotoAdapter(applicationContext, this)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            adapter = photoAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoRepository.getPhotos().observe(this, Observer { it -> photoAdapter.submitList(it) })
    }

    override fun onPhotoClicked(photo: Photo?) {
        val bundle = Bundle()
        bundle.putParcelable("photo", photo)
        view?.findNavController()?.navigate(R.id.photoViewFragment, bundle)
    }

}


interface PhotoClickListener {
    fun onPhotoClicked(photo: Photo?)
}
