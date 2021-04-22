package com.example.giser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.giser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var user: User

    companion object {
        const val EXTRA_USER = "extra_user"
        private val TAB_TITLES = intArrayOf(
                R.string.Followers,
                R.string.Following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        binding.root
        setContentView(R.layout.activity_detail)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        sectionsPagerAdapter.username = user.username.toString()
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) {
            tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        setData()
    }

    private fun setData() {
        intent.getParcelableExtra<User>(EXTRA_USER) as User
        fun bind(user: User) {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(user.avatar)
                    .into(imgDtlavtr)
                txtDtlname.text = user.name
                txtDtluname.text = user.username
                txtCmpny.text = user.company
                txtLctn.text = user.location
                txtRpstry.text = user.repository
            }
        }
    }
}