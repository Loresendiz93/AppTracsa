import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _mensajeConcatenado = MutableLiveData<String>()
    val mensajeConcatenado: LiveData<String> get() = _mensajeConcatenado
    val images = mutableStateListOf<String>()

    fun addMessage(message: String) {
        val currentMessage = _mensajeConcatenado.value ?: ""
        val newMessage = currentMessage + message
        _mensajeConcatenado.value = newMessage
    }
    fun setConcatenatedMessage(message: String) {
        _mensajeConcatenado.value = message
    }
    fun addImage(imagePath: String) {
        images.add(imagePath)
    }

}
