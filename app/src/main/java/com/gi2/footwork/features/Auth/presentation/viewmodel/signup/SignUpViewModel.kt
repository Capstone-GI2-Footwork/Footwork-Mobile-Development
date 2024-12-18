package com.gi2.footwork.features.Auth.presentation.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gi2.footwork.common.helper.UiStatus
import com.gi2.footwork.features.Auth.domain.use_case.SignUpParams
import com.gi2.footwork.features.Auth.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel(), ContainerHost<SignUpState, SignUpSideEffect> {

    override val container =
        viewModelScope.container<SignUpState, SignUpSideEffect>(
            SignUpState.initial()
        )

    fun onFullNameChanged(name: String) {
        intent {
            reduce {
                state.copy(
                    form = state.form.copy(
                        fullName = name,
                        fullNameError = state.form.validateFullName()
                    )
                )
            }
        }
    }

    fun onEmailChanged(email: String) {
        intent {
            reduce {
                state.copy(
                    form = state.form.copy(
                        email = email,
                        emailError = state.form.validateEmail()
                    )
                )
            }
        }
    }

    fun onPasswordChanged(password: String) {
        intent {
            reduce {
                state.copy(
                    form = state.form.copy(
                        password = password,
                        passwordError = state.form.validatePassword()
                    )
                )
            }
        }
    }

    fun onSubmit() {
        intent {
            if (state.form.isValid) {
                reduce { state.copy(status = UiStatus.Loading) }

                val response = signUpUseCase(
                    SignUpParams(
                        fullName = state.form.fullName,
                        email = state.form.email,
                        password = state.form.password
                    )
                )

                if (response.isSuccess) {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(SignUpSideEffect.OnSuccessNavigate)
                } else {
                    reduce { state.copy(status = UiStatus.Failure("Something went wrong")) }
                }

            }
        }
    }
}