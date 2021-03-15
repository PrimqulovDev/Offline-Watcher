package app.test.offlinewatcher.ui.screens.nav.page_my_videos

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.test.offlinewatcher.R
import app.test.offlinewatcher.repo.db.video.VideoEntity
import app.test.offlinewatcher.utils.extensions.inflate
import com.bumptech.glide.Glide

class VideosAdapter : ListAdapter<VideoEntity, VideosAdapter.ViewHolder>(DiffItem) {

    var onItemClickListener: ((videoUrl: String) -> Unit)? = null

    object DiffItem : DiffUtil.ItemCallback<VideoEntity>() {
        override fun areItemsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoEntity, newItem: VideoEntity): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.decodedUrl == newItem.decodedUrl
                    && oldItem.localUrl == newItem.localUrl
                    && oldItem.remoteUrl == newItem.remoteUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_video_local))


    override fun onBindViewHolder(h: ViewHolder, position: Int) = h.bind()
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val ivThumbnail: AppCompatImageView = v.findViewById(R.id.videoThumbnail)
        private val tvTitle: AppCompatTextView = v.findViewById(R.id.tvTitle)

        fun bind() {
            val d = getItem(adapterPosition)
            Log.d("VIDEOS", "bind: decoded url -> ${d.decodedUrl}")
            Glide.with(itemView)
                .load(d.decodedUrl)
                .placeholder(R.color.black)
                .error(R.color.black)
                .into(ivThumbnail)

            tvTitle.text = d.title
            itemView.setOnClickListener {
                val url = if (d.decodedUrl.isEmpty()) d.localUrl else d.decodedUrl
                onItemClickListener?.invoke(url)
            }

        }
    }
}