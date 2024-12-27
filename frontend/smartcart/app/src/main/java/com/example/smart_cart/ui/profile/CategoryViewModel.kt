//package com.example.smart_cart.ui.profile
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.smart_cart.data.model.Category
//import com.example.smart_cart.data.api.CategoryRepository
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {
//    val categories: StateFlow<List<Category>> = repository.categories
//
//    init {
//        fetchCategories()
//    }
//
//    private fun fetchCategories() {
//        viewModelScope.launch {
//            repository.fetchCategories()
//        }
//    }
//}
