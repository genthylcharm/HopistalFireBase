import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hopistalfirebase.Domain.CategoryModel
import com.example.hopistalfirebase.Domain.DoctorModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel : ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _doctors = MutableLiveData<MutableList<DoctorModel>>()

    val category: LiveData<MutableList<CategoryModel>> = _category
    val doctors: LiveData<MutableList<DoctorModel>> = _doctors

    // Hàm loadCategory để tải dữ liệu Category từ Firebase
    fun loadCategory() {
        val categoryRef = firebaseDatabase.getReference("Category")
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val category = childSnapshot.getValue(CategoryModel::class.java)
                    if (category != null) {
                        categoryList.add(category)
                    }
                }
                _category.value = categoryList // Cập nhật danh sách Category vào LiveData
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

    // Hàm loadDoctors để tải dữ liệu Doctor từ Firebase
    fun loadDoctors() {
        val doctorsRef = firebaseDatabase.getReference("Doctors")
        doctorsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val doctorList = mutableListOf<DoctorModel>()
                for (childSnapshot in snapshot.children) {
                    val doctor = childSnapshot.getValue(DoctorModel::class.java)
                    if (doctor != null) {
                        doctorList.add(doctor)
                    }
                }
                _doctors.value = doctorList // Cập nhật danh sách Doctor vào LiveData
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }
}
