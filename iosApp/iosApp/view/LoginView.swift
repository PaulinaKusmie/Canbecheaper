import SwiftUI
import shared

struct LoginView: View {

@StateObject private var vm = LoginViewModel(
    userRepository: KotlinHelper().getUserRepository(),
    userSession: KotlinHelper().getUserSession()
    )

@Stare private var email: String = ""




}