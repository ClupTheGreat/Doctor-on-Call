package com.iiser.doctor_on_call.presentation.quiz

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiser.doctor_on_call.data.model.DiagnosisResultItemModel
import com.iiser.doctor_on_call.data.model.DiseaseTreatmentModel
import com.iiser.doctor_on_call.data.model.QuestionAndOptionsModel
import com.iiser.doctor_on_call.data.repository.IResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val diagnosisRepository: IResultRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuizScreenUIState())
    val uiState = _uiState.asStateFlow()


    //Questions List

//    private val questions = listOf(
//        QuestionAndOptionsModel(
//            "Which of these fever-related symptoms do you have? (Select all that apply)",
//            listOf("Fever", "Fever or chills", "Fever &/or chills", "Fever with night sweats")
//        ),
//        QuestionAndOptionsModel(
//            "Which of these respiratory symptoms do you have? (Select all that apply)",
//            listOf("Runny nose", "Cough with greenish-yellow mucus", "Shortness of breath", "Coughing")
//        ),
//        QuestionAndOptionsModel(
//            "Which of these throat-related symptoms do you have? (Select all that apply)",
//            listOf("Sore throat", "Painful swallowing", "Occasionally throat pain", "White patches on tonsils")
//        ),
//        QuestionAndOptionsModel(
//            "Which of these general body symptoms do you have? (Select all that apply)",
//            listOf("Malaise (general discomfort)", "Severe body ache", "Body ache", "Fatigue", "Loss of weight")
//        ),
//        QuestionAndOptionsModel(
//            "Which of these digestive symptoms do you have? (Select all that apply)",
//            listOf("Nausea", "Abdominal pain", "Loss of appetite")
//        ),
//        QuestionAndOptionsModel(
//            "Which of these other symptoms do you have? (Select all that apply)",
//            listOf("Skin rash or red spots", "Loss of taste or smell", "Swollen tender lymph nodes")
//        )
//    )

    // TODO
//    private val questions = listOf(
//        // Head-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the head do you have? (Select all that apply)",
//            listOf("Headache", "Dizziness", "Blurred vision", "Nausea"),
//            "Head"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the head do you have? (Select all that apply)",
//            listOf("Sensitivity to light", "Tingling sensation", "Neck pain", "Difficulty concentrating"),
//            "Head"
//        ),
//
//        // Chest-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the chest do you have? (Select all that apply)",
//            listOf("Chest pain", "Shortness of breath", "Cough", "Wheezing"),
//            "Chest"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the chest do you have? (Select all that apply)",
//            listOf("Palpitations", "Chest tightness", "Coughing up blood", "Fatigue"),
//            "Chest"
//        ),
//
//        // Abdomen-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the abdomen do you have? (Select all that apply)",
//            listOf("Stomach pain", "Nausea", "Vomiting", "Loss of appetite"),
//            "Abdomen"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the abdomen do you have? (Select all that apply)",
//            listOf("Bloating", "Diarrhea", "Constipation", "Indigestion"),
//            "Abdomen"
//        ),
//
//        // Pelvis-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the pelvis do you have? (Select all that apply)",
//            listOf("Pelvic pain", "Urinary urgency", "Painful urination", "Menstrual cramps"),
//            "Pelvis"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the pelvis do you have? (Select all that apply)",
//            listOf("Lower back pain", "Vaginal discharge", "Pain during intercourse", "Urinary retention"),
//            "Pelvis"
//        ),
//
//        // Arms-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the arms do you have? (Select all that apply)",
//            listOf("Arm pain", "Numbness", "Weakness", "Swelling"),
//            "Arms"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the arms do you have? (Select all that apply)",
//            listOf("Tingling sensation", "Stiffness", "Reduced range of motion", "Burning sensation"),
//            "Arms"
//        ),
//
//        // Legs-related symptoms
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the legs do you have? (Select all that apply)",
//            listOf("Leg pain", "Swelling", "Numbness", "Cramps"),
//            "Legs"
//        ),
//        QuestionAndOptionsModel(
//            "Which of these symptoms related to the legs do you have? (Select all that apply)",
//            listOf("Tingling in the legs", "Weakness", "Leg stiffness", "Pain in the calf"),
//            "Legs"
//        )
//    )
    private val questions = listOf(
        // Head-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the head do you have? (Select all that apply)",
            listOf("Headache", "Dizziness", "Blurred vision", "Nausea"),
            "Head"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the head do you have? (Select all that apply)",
            listOf("Sensitivity to light", "Tingling sensation", "Neck pain", "Difficulty concentrating"),
            "Head"
        ),
        QuestionAndOptionsModel(
            "Which additional head symptoms are you experiencing? (Select all that apply)",
            listOf("Ear pain", "Ringing in ears", "Facial pain", "Jaw pain"),
            "Head"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these eye or vision symptoms? (Select all that apply)",
            listOf("Eye redness", "Watery eyes", "Double vision", "Eye pain"),
            "Head"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms related to your head? (Select all that apply)",
            listOf("Sinus pressure", "Memory problems", "Confusion", "Fainting episodes"),
            "Head"
        ),

        // Neck-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the neck do you have? (Select all that apply)",
            listOf("Neck pain", "Stiffness", "Limited range of motion", "Muscle spasms"),
            "Neck"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these additional neck symptoms? (Select all that apply)",
            listOf("Pain radiating to shoulders", "Pain radiating to arms", "Numbness", "Tingling"),
            "Neck"
        ),
        QuestionAndOptionsModel(
            "Which of these neck-related symptoms are you experiencing? (Select all that apply)",
            listOf("Difficulty swallowing", "Swollen lymph nodes", "Visible lumps", "Throat pain"),
            "Neck"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms with your neck? (Select all that apply)",
            listOf("Grinding sensation", "Popping sounds", "Pain when looking up", "Pain when looking down"),
            "Neck"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms affecting your neck area do you have? (Select all that apply)",
            listOf("Tenderness to touch", "Pain that worsens with movement", "Pain that improves with rest", "Neck muscle weakness"),
            "Neck"
        ),

        // Shoulders-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to your shoulders do you have? (Select all that apply)",
            listOf("Shoulder pain", "Limited mobility", "Stiffness", "Swelling"),
            "Shoulders"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these shoulder symptoms? (Select all that apply)",
            listOf("Pain when lifting arm", "Pain at night", "Weakness", "Clicking sounds"),
            "Shoulders"
        ),
        QuestionAndOptionsModel(
            "Which of these additional shoulder symptoms do you have? (Select all that apply)",
            listOf("Pain between shoulder blades", "Numbness", "Tingling", "Muscle spasms"),
            "Shoulders"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your shoulders? (Select all that apply)",
            listOf("Joint instability", "Recurrent dislocations", "Bruising", "Visible deformity"),
            "Shoulders"
        ),
        QuestionAndOptionsModel(
            "Which of these shoulder-related symptoms are you experiencing? (Select all that apply)",
            listOf("Pain radiating down arm", "Inability to carry objects", "Pain when reaching behind", "Difficulty dressing"),
            "Shoulders"
        ),

        // Chest-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the chest do you have? (Select all that apply)",
            listOf("Chest pain", "Shortness of breath", "Cough", "Wheezing"),
            "Chest"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the chest do you have? (Select all that apply)",
            listOf("Palpitations", "Chest tightness", "Coughing up blood", "Fatigue"),
            "Chest"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these additional chest symptoms? (Select all that apply)",
            listOf("Pain when breathing deeply", "Rapid breathing", "Irregular heartbeat", "Chest burning"),
            "Chest"
        ),
        QuestionAndOptionsModel(
            "Which of these chest-related symptoms do you have? (Select all that apply)",
            listOf("Pain radiating to arm/jaw", "Pressure sensation", "Sweating with chest pain", "Anxiety with chest symptoms"),
            "Chest"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your chest area? (Select all that apply)",
            listOf("Coughing up mucus", "Difficulty lying flat", "Swollen ankles", "Blue lips or fingernails"),
            "Chest"
        ),

        // Arms-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the arms do you have? (Select all that apply)",
            listOf("Arm pain", "Numbness", "Weakness", "Swelling"),
            "Arms"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the arms do you have? (Select all that apply)",
            listOf("Tingling sensation", "Stiffness", "Reduced range of motion", "Burning sensation"),
            "Arms"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these additional arm symptoms? (Select all that apply)",
            listOf("Joint pain", "Muscle spasms", "Skin rash", "Discoloration"),
            "Arms"
        ),
        QuestionAndOptionsModel(
            "Which of these arm-related symptoms do you have? (Select all that apply)",
            listOf("Pain when lifting objects", "Difficulty straightening arm", "Muscle wasting", "Visible deformity"),
            "Arms"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your arms? (Select all that apply)",
            listOf("Cold sensation", "Pain at night", "Cramping", "Painful to touch"),
            "Arms"
        ),

        // Hands-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to your hands do you have? (Select all that apply)",
            listOf("Hand pain", "Numbness", "Tingling", "Stiffness"),
            "Hands"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these hand symptoms? (Select all that apply)",
            listOf("Swelling", "Redness", "Joint pain", "Weakness"),
            "Hands"
        ),
        QuestionAndOptionsModel(
            "Which of these hand-related symptoms do you have? (Select all that apply)",
            listOf("Difficulty gripping objects", "Finger locking", "Visible deformity", "Skin changes"),
            "Hands"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your hands? (Select all that apply)",
            listOf("Tremors", "Cold sensation", "Excessive sweating", "Color changes"),
            "Hands"
        ),
        QuestionAndOptionsModel(
            "Which of these additional hand symptoms are you experiencing? (Select all that apply)",
            listOf("Pain traveling up the arm", "Sensation of pins and needles", "Loss of coordination", "Difficulty with fine movements"),
            "Hands"
        ),

        // Abdomen-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the abdomen do you have? (Select all that apply)",
            listOf("Stomach pain", "Nausea", "Vomiting", "Loss of appetite"),
            "Abdomen"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the abdomen do you have? (Select all that apply)",
            listOf("Bloating", "Diarrhea", "Constipation", "Indigestion"),
            "Abdomen"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these additional abdominal symptoms? (Select all that apply)",
            listOf("Visible swelling", "Tenderness to touch", "Heartburn", "Acid reflux"),
            "Abdomen"
        ),
        QuestionAndOptionsModel(
            "Which of these abdomen-related symptoms do you have? (Select all that apply)",
            listOf("Blood in stool", "Black stool", "Yellowing of skin/eyes", "Weight loss"),
            "Abdomen"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your abdominal area? (Select all that apply)",
            listOf("Cramping", "Pain after eating", "Excessive gas", "Gurgling sounds"),
            "Abdomen"
        ),

        // Pelvis-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the pelvis do you have? (Select all that apply)",
            listOf("Pelvic pain", "Urinary urgency", "Painful urination", "Menstrual cramps"),
            "Pelvis"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the pelvis do you have? (Select all that apply)",
            listOf("Lower back pain", "Vaginal discharge", "Pain during intercourse", "Urinary retention"),
            "Pelvis"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these additional pelvic symptoms? (Select all that apply)",
            listOf("Blood in urine", "Frequent urination", "Pelvic pressure", "Difficulty starting urination"),
            "Pelvis"
        ),
        QuestionAndOptionsModel(
            "Which of these pelvis-related symptoms do you have? (Select all that apply)",
            listOf("Irregular menstruation", "Rectal pain", "Genital itching", "Visible swelling"),
            "Pelvis"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your pelvic area? (Select all that apply)",
            listOf("Pain radiating to buttocks", "Pain with bowel movements", "Bulging sensation", "Urinary incontinence"),
            "Pelvis"
        ),

        // Thighs-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to your thighs do you have? (Select all that apply)",
            listOf("Thigh pain", "Muscle weakness", "Numbness", "Tingling"),
            "Thighs"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these thigh symptoms? (Select all that apply)",
            listOf("Swelling", "Muscle cramps", "Pain when walking", "Pain at rest"),
            "Thighs"
        ),
        QuestionAndOptionsModel(
            "Which of these thigh-related symptoms do you have? (Select all that apply)",
            listOf("Skin rash", "Bruising", "Visible deformity", "Warmth"),
            "Thighs"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your thighs? (Select all that apply)",
            listOf("Pain radiating from back", "Pain radiating to knee", "Stiffness", "Difficulty standing"),
            "Thighs"
        ),
        QuestionAndOptionsModel(
            "Which of these additional thigh symptoms are you experiencing? (Select all that apply)",
            listOf("Muscle spasms", "Pain when climbing stairs", "Clicking sounds", "Feeling of instability"),
            "Thighs"
        ),

        // Calves-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to the legs do you have? (Select all that apply)",
            listOf("Leg pain", "Swelling", "Numbness", "Cramps"),
            "Calves"
        ),
        QuestionAndOptionsModel(
            "Which of these symptoms related to the legs do you have? (Select all that apply)",
            listOf("Tingling in the legs", "Weakness", "Leg stiffness", "Pain in the calf"),
            "Calves"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these calf symptoms? (Select all that apply)",
            listOf("Warmth in affected area", "Redness", "Tenderness to touch", "Visible veins"),
            "Calves"
        ),
        QuestionAndOptionsModel(
            "Which of these calf-related symptoms do you have? (Select all that apply)",
            listOf("Pain when walking", "Pain at night", "Pain that improves with walking", "Pain at rest"),
            "Calves"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your calves? (Select all that apply)",
            listOf("Pain after exercise", "Muscle tightness", "Skin changes", "Cold sensation"),
            "Calves"
        ),

        // Feet-related symptoms (5 questions)
        QuestionAndOptionsModel(
            "Which of these symptoms related to your feet do you have? (Select all that apply)",
            listOf("Foot pain", "Swelling", "Numbness", "Tingling"),
            "Feet"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these foot symptoms? (Select all that apply)",
            listOf("Pain when walking", "Pain at rest", "Burning sensation", "Itching"),
            "Feet"
        ),
        QuestionAndOptionsModel(
            "Which of these foot-related symptoms do you have? (Select all that apply)",
            listOf("Skin changes", "Discoloration", "Nail problems", "Visible deformity"),
            "Feet"
        ),
        QuestionAndOptionsModel(
            "Are you experiencing any of these symptoms in your feet? (Select all that apply)",
            listOf("Heel pain", "Arch pain", "Ball of foot pain", "Toe pain"),
            "Feet"
        ),
        QuestionAndOptionsModel(
            "Which of these additional foot symptoms are you experiencing? (Select all that apply)",
            listOf("Cold feet", "Excessive sweating", "Open sores", "Difficulty bearing weight"),
            "Feet"
        )
    )


    // Diseases

//    val diseases = listOf(
//        DiseaseTreatmentModel(
//            "Common Cold",
//            listOf("Fever", "Malaise", "Runny nose", "Occasionally throat pain"),
//            listOf("Analgesic", "Antipyretic", "Gargles")
//        ),
//        DiseaseTreatmentModel(
//            "Influenza",
//            listOf("Fever", "Sore throat", "Body ache", "Fatigue"),
//            listOf("Rest", "Fluids", "Analgesics", "Decongestant")
//        ),
//        DiseaseTreatmentModel(
//            "Covid-19",
//            listOf("Fever or chills", "Sore throat", "Loss of taste or smell", "Fatigue"),
//            listOf("Rest", "Fluids", "Antipyretics", "Safe distancing", "Mask")
//        ),
//        DiseaseTreatmentModel(
//            "Streptococcus Throat Infection",
//            listOf("Painful swallowing", "Fever", "Swollen tender lymph nodes", "White patches on tonsils"),
//            listOf("Analgesic", "Antipyretic", "Gargles", "Throat lozenges")
//        ),
//        DiseaseTreatmentModel(
//            "Pneumonia",
//            listOf("Fever &/or chills", "Cough with greenish yellow mucus", "Shortness of breath", "Loss of appetite"),
//            listOf("Antipyretic", "Antibiotic", "Rest", "Chest X-ray")
//        ),
//        DiseaseTreatmentModel(
//            "Dengue",
//            listOf("Fever &/or chills", "Severe body ache", "Nausea", "Skin rash or red spots"),
//            listOf("Fluids", "Antipyretic", "Laboratory tests", "Rest")
//        ),
//        DiseaseTreatmentModel(
//            "Hepatitis A",
//            listOf("Fatigue", "Nausea", "Abdominal pain", "Loss of appetite"),
//            listOf("Rest", "Fluids", "Avoid alcohol")
//        ),
//        DiseaseTreatmentModel(
//            "Tuberculosis",
//            listOf("Fever with night sweats", "Loss of weight", "Loss of appetite", "Coughing"),
//            listOf("Chest X-ray", "(mHealthcare) Chest physician")
//        )
//    )
//TODO
//    val diseases = listOf(
//        // Head-related Diseases
//        DiseaseTreatmentModel(
//            "Migraine",
//            listOf("Headache", "Sensitivity to light", "Dizziness", "Nausea"),
//            listOf("💊 Pain relievers", "😴 Rest", "💧 Hydration", "🤢 Anti-nausea medication")
//        ),
//        DiseaseTreatmentModel(
//            "Tension Headache",
//            listOf("Headache", "Neck pain", "Difficulty concentrating", "Sensitivity to light"),
//            listOf("💊 Analgesics", "🧘 Relaxation techniques", "🧠 Stress management")
//        ),
//        DiseaseTreatmentModel(
//            "Concussion",
//            listOf("Headache", "Dizziness", "Nausea", "Blurred vision", "Sensitivity to light"),
//            listOf("😴 Rest", "🧊 Ice packs", "💊 Pain relievers", "🩺 Monitoring for worsening symptoms")
//        ),
//
//        // Chest-related Diseases
//        DiseaseTreatmentModel(
//            "Pneumonia",
//            listOf("Chest pain", "Shortness of breath", "Cough with greenish-yellow mucus", "Fatigue"),
//            listOf("💊 Antibiotics", "🌡️ Antipyretics", "😴 Rest", "🩻 Chest X-ray")
//        ),
//        DiseaseTreatmentModel(
//            "Asthma",
//            listOf("Shortness of breath", "Coughing", "Wheezing", "Chest tightness"),
//            listOf("💨 Inhalers (bronchodilators)", "🧪 Anti-inflammatory drugs", "😴 Rest")
//        ),
//        DiseaseTreatmentModel(
//            "Myocardial Infarction",
//            listOf("Chest pain", "Shortness of breath", "Fatigue", "Sweating"),
//            listOf("🚨 Emergency care", "💊 Aspirin", "💧 Nitroglycerin", "🩸 Antiplatelet drugs")
//        ),
//
//        // Abdomen-related Diseases
//        DiseaseTreatmentModel(
//            "Gastroenteritis",
//            listOf("Stomach pain", "Nausea", "Vomiting", "Loss of appetite"),
//            listOf("💧 Hydration", "🤢 Anti-nausea medication", "😴 Rest")
//        ),
//        DiseaseTreatmentModel(
//            "Irritable Bowel Syndrome",
//            listOf("Stomach pain", "Bloating", "Diarrhea", "Constipation"),
//            listOf("🥗 Dietary changes", "💊 Anti-spasmodic medication", "🧘 Stress management")
//        ),
//        DiseaseTreatmentModel(
//            "Hepatitis A",
//            listOf("Abdominal pain", "Nausea", "Loss of appetite", "Fatigue"),
//            listOf("😴 Rest", "💧 Fluids", "🚫 Avoid alcohol")
//        ),
//
//        // Pelvis-related Diseases
//        DiseaseTreatmentModel(
//            "Urinary Tract Infection",
//            listOf("Painful urination", "Urinary urgency", "Lower abdominal pain"),
//            listOf("💊 Antibiotics", "💧 Increased fluid intake", "💊 Pain relievers")
//        ),
//        DiseaseTreatmentModel(
//            "Endometriosis",
//            listOf("Pelvic pain", "Painful menstruation", "Painful intercourse"),
//            listOf("💊 Pain relievers", "💉 Hormonal therapy", "🔪 Surgery")
//        ),
//        DiseaseTreatmentModel(
//            "Pelvic Inflammatory Disease",
//            listOf("Pelvic pain", "Abnormal vaginal discharge", "Painful urination"),
//            listOf("💊 Antibiotics", "💊 Pain relievers", "🔪 Surgery")
//        ),
//
//        // Arms-related Diseases
//        DiseaseTreatmentModel(
//            "Carpal Tunnel Syndrome",
//            listOf("Numbness", "Tingling sensation", "Weakness in the hands", "Arm pain"),
//            listOf("🧤 Wrist splints", "💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy")
//        ),
//        DiseaseTreatmentModel(
//            "Frozen Shoulder",
//            listOf("Shoulder pain", "Reduced range of motion", "Arm stiffness"),
//            listOf("🏃‍♂️ Physical therapy", "💊 Pain relievers", "💉 Injections")
//        ),
//        DiseaseTreatmentModel(
//            "Tennis Elbow",
//            listOf("Pain and tenderness on the outer elbow", "Weakness in the forearm", "Arm pain"),
//            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory medications", "🏃 Physical therapy")
//        ),
//
//        // Legs-related Diseases
//        DiseaseTreatmentModel(
//            "Deep Vein Thrombosis",
//            listOf("Leg swelling", "Pain in the calf", "Warmth in the affected area"),
//            listOf("🩸 Anticoagulants (blood thinners)", "🧦 Compression stockings", "🦵 Elevation of the leg")
//        ),
//        DiseaseTreatmentModel(
//            "Osteoarthritis of the Knee",
//            listOf("Knee pain", "Stiffness", "Swelling", "Reduced range of motion"),
//            listOf("💊 Pain relievers", "🏃 Physical therapy", "🦿 Joint replacement (in severe cases)")
//        ),
//        DiseaseTreatmentModel(
//            "Peripheral Artery Disease",
//            listOf("Leg pain", "Numbness", "Cold feet", "Weak pulses in the legs"),
//            listOf("🚶 Exercise", "🩸 Antiplatelet medications", "🩻 Angioplasty or bypass surgery")
//        )
//    )

//    fun filterQuestionsByRegions(selectedRegionsText: String?) {
//        selectedRegionsText?.let { regions ->
//            val selectedRegions = regions.split(",").map { it.trim() }
//
//            // Filter the questions based on selected regions
//            val filteredQuestions = allQuestions.filter { question ->
//                selectedRegions.any { region ->
//                    question.bodyRegion.contains(region, ignoreCase = true) // Case insensitive matching
//                }
//            }
//
//            // Update the UI state with the filtered questions
//            _uiState.value = _uiState.value.copy(filteredQuestions = filteredQuestions)
//        } ?: run {
//            // If no regions are selected, return all questions
//            _uiState.value = _uiState.value.copy(filteredQuestions = allQuestions)
//        }
//    }

    val diseases = listOf(
        // Head-related Diseases
        DiseaseTreatmentModel(
            "Migraine",
            listOf("Headache", "Sensitivity to light", "Nausea", "Blurred vision"),
            listOf("💊 Pain relievers", "😴 Rest in dark room", "💧 Hydration", "🤢 Anti-nausea medication")
        ),
        DiseaseTreatmentModel(
            "Tension Headache",
            listOf("Headache", "Neck pain", "Difficulty concentrating", "Tenderness to touch"),
            listOf("💊 Analgesics", "🧘 Relaxation techniques", "🧠 Stress management", "🔥 Warm compress")
        ),
        DiseaseTreatmentModel(
            "Concussion",
            listOf("Headache", "Dizziness", "Memory problems", "Confusion"),
            listOf("😴 Rest", "🧊 Ice packs", "💊 Pain relievers", "🩺 Monitoring for worsening symptoms")
        ),
        DiseaseTreatmentModel(
            "Sinusitis",
            listOf("Sinus pressure", "Facial pain", "Headache", "Nasal congestion"),
            listOf("💊 Decongestants", "💦 Saline nasal spray", "💨 Steam inhalation", "🔥 Warm compress")
        ),
        DiseaseTreatmentModel(
            "Otitis Media (Ear Infection)",
            listOf("Ear pain", "Ringing in ears", "Difficulty hearing", "Dizziness"),
            listOf("💊 Antibiotics", "💊 Pain relievers", "🧪 Ear drops", "🔥 Warm compress")
        ),
        DiseaseTreatmentModel(
            "Glaucoma",
            listOf("Eye pain", "Blurred vision", "Headache", "Eye redness"),
            listOf("💧 Eye drops", "💊 Oral medications", "🔬 Regular eye exams", "👁️ Surgery (if necessary)")
        ),
        DiseaseTreatmentModel(
            "Vestibular Neuritis",
            listOf("Dizziness", "Difficulty concentrating", "Nausea", "Balance problems"),
            listOf("💊 Anti-vertigo medication", "😴 Rest", "🧘 Vestibular rehabilitation", "💊 Anti-nausea medication")
        ),

        // Neck-related Diseases
        DiseaseTreatmentModel(
            "Cervical Spondylosis",
            listOf("Neck pain", "Stiffness", "Pain radiating to shoulders", "Limited range of motion"),
            listOf("💊 Pain relievers", "🔥 Heat therapy", "🏃‍♀️ Physical therapy", "🧠 Stress management")
        ),
        DiseaseTreatmentModel(
            "Whiplash",
            listOf("Neck pain", "Pain when looking up", "Pain when looking down", "Stiffness"),
            listOf("🧊 Ice in first 24 hours", "🔥 Heat after 24 hours", "💊 Pain medication", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Torticollis (Wry Neck)",
            listOf("Neck pain", "Neck muscle spasms", "Limited range of motion", "Head tilted to one side"),
            listOf("💊 Muscle relaxants", "🔥 Heat therapy", "💉 Botox injections (severe cases)", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Cervical Herniated Disc",
            listOf("Neck pain", "Pain radiating to arms", "Numbness", "Tingling"),
            listOf("💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy", "😴 Rest", "💉 Steroid injections (if needed)")
        ),
        DiseaseTreatmentModel(
            "Lymphadenitis",
            listOf("Swollen lymph nodes", "Neck pain", "Tenderness to touch", "Throat pain"),
            listOf("💊 Antibiotics", "🧊 Cold compresses", "😴 Rest", "💊 Anti-inflammatory drugs")
        ),

        // Shoulders-related Diseases
        DiseaseTreatmentModel(
            "Rotator Cuff Tear",
            listOf("Shoulder pain", "Pain when lifting arm", "Weakness", "Limited mobility"),
            listOf("🧊 Ice therapy", "💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy", "🔪 Surgery (severe cases)")
        ),
        DiseaseTreatmentModel(
            "Frozen Shoulder",
            listOf("Shoulder pain", "Stiffness", "Limited mobility", "Pain at night"),
            listOf("🏃‍♀️ Physical therapy", "💊 Pain relievers", "💉 Steroid injections", "🧘 Gentle stretching")
        ),
        DiseaseTreatmentModel(
            "Shoulder Impingement",
            listOf("Shoulder pain", "Pain when lifting arm", "Pain radiating down arm", "Difficulty reaching behind"),
            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Shoulder Dislocation",
            listOf("Shoulder pain", "Joint instability", "Visible deformity", "Limited mobility"),
            listOf("🦾 Reduction (putting joint back in place)", "🧊 Ice", "💊 Pain medication", "🧵 Immobilization")
        ),
        DiseaseTreatmentModel(
            "Acromioclavicular Joint Sprain",
            listOf("Pain between shoulder blades", "Swelling", "Bruising", "Pain when reaching across body"),
            listOf("🧊 Ice", "💊 Pain relievers", "🧵 Shoulder sling", "😴 Rest")
        ),

        // Chest-related Diseases
        DiseaseTreatmentModel(
            "Pneumonia",
            listOf("Chest pain", "Shortness of breath", "Coughing up mucus", "Fatigue"),
            listOf("💊 Antibiotics", "🌡️ Antipyretics", "😴 Rest", "💧 Hydration")
        ),
        DiseaseTreatmentModel(
            "Asthma",
            listOf("Shortness of breath", "Wheezing", "Chest tightness", "Cough"),
            listOf("💨 Inhalers (bronchodilators)", "🧪 Anti-inflammatory drugs", "😴 Rest", "🏡 Allergen avoidance")
        ),
        DiseaseTreatmentModel(
            "Myocardial Infarction (Heart Attack)",
            listOf("Chest pain", "Pain radiating to arm/jaw", "Shortness of breath", "Sweating with chest pain"),
            listOf("🚨 Emergency care", "💊 Aspirin", "💧 Nitroglycerin", "🩸 Antiplatelet drugs")
        ),
        DiseaseTreatmentModel(
            "Pulmonary Embolism",
            listOf("Chest pain", "Shortness of breath", "Rapid breathing", "Coughing up blood"),
            listOf("🚨 Emergency care", "🩸 Anticoagulants", "💉 Thrombolytics (severe cases)", "🧦 Compression stockings")
        ),
        DiseaseTreatmentModel(
            "Congestive Heart Failure",
            listOf("Shortness of breath", "Fatigue", "Swollen ankles", "Difficulty lying flat"),
            listOf("💊 ACE inhibitors", "💊 Beta blockers", "💧 Diuretics", "🧂 Low-sodium diet")
        ),
        DiseaseTreatmentModel(
            "Gastroesophageal Reflux Disease (GERD)",
            listOf("Chest burning", "Acid reflux", "Heartburn", "Chest pain"),
            listOf("💊 Antacids", "💊 Proton pump inhibitors", "🥗 Dietary changes", "🛌 Elevate head while sleeping")
        ),

        // Arms-related Diseases
        DiseaseTreatmentModel(
            "Tennis Elbow",
            listOf("Arm pain", "Pain when lifting objects", "Weakness", "Tenderness outside elbow"),
            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory medications", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Golfer's Elbow",
            listOf("Arm pain", "Pain on inside of elbow", "Stiffness", "Weakness"),
            listOf("🧊 Ice", "💊 Pain relievers", "🧵 Brace or strap", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Bicep Tendonitis",
            listOf("Arm pain", "Pain at front of shoulder", "Pain when lifting", "Muscle weakness"),
            listOf("🧊 Ice", "😴 Rest", "💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Cubital Tunnel Syndrome",
            listOf("Numbness", "Tingling sensation", "Pain at night", "Weakness"),
            listOf("💊 Anti-inflammatory medication", "🧵 Elbow brace", "🏃‍♀️ Physical therapy", "🔪 Surgery (severe cases)")
        ),
        DiseaseTreatmentModel(
            "Brachial Plexus Injury",
            listOf("Arm pain", "Reduced range of motion", "Burning sensation", "Muscle wasting"),
            listOf("🏃‍♀️ Physical therapy", "💊 Pain medication", "🧠 Nerve stimulation", "🔪 Surgery (severe cases)")
        ),

        // Hands-related Diseases
        DiseaseTreatmentModel(
            "Carpal Tunnel Syndrome",
            listOf("Hand pain", "Numbness", "Tingling", "Weakness"),
            listOf("🧤 Wrist splints", "💊 Anti-inflammatory drugs", "🏃‍♀️ Physical therapy", "🔪 Surgery (severe cases)")
        ),
        DiseaseTreatmentModel(
            "Rheumatoid Arthritis (Hands)",
            listOf("Hand pain", "Joint pain", "Swelling", "Stiffness"),
            listOf("💊 NSAIDs", "💊 Disease-modifying antirheumatic drugs", "🏃‍♀️ Physical therapy", "🔥 Warm compresses")
        ),
        DiseaseTreatmentModel(
            "Trigger Finger",
            listOf("Finger locking", "Pain in finger", "Stiffness", "Popping sensation"),
            listOf("😴 Rest", "🧵 Splinting", "💉 Steroid injections", "🔪 Surgery (severe cases)")
        ),
        DiseaseTreatmentModel(
            "De Quervain's Tenosynovitis",
            listOf("Hand pain", "Thumb pain", "Pain when gripping", "Swelling"),
            listOf("💊 Anti-inflammatory medication", "🧊 Ice therapy", "🧵 Thumb splint", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Raynaud's Phenomenon",
            listOf("Cold sensation", "Color changes", "Numbness", "Tingling"),
            listOf("🧤 Keep hands warm", "🚭 Avoid smoking", "💊 Medications to improve circulation", "🧘 Stress management")
        ),

        // Abdomen-related Diseases
        DiseaseTreatmentModel(
            "Gastroenteritis",
            listOf("Stomach pain", "Nausea", "Vomiting", "Diarrhea"),
            listOf("💧 Hydration", "🤢 Anti-nausea medication", "😴 Rest", "💊 Probiotics")
        ),
        DiseaseTreatmentModel(
            "Irritable Bowel Syndrome",
            listOf("Stomach pain", "Bloating", "Diarrhea", "Constipation"),
            listOf("🥗 Dietary changes", "💊 Anti-spasmodic medication", "🧘 Stress management", "🧪 Probiotics")
        ),
        DiseaseTreatmentModel(
            "Appendicitis",
            listOf("Pain in lower right abdomen", "Nausea", "Loss of appetite", "Tenderness to touch"),
            listOf("🚨 Emergency care", "🔪 Surgery", "💊 Antibiotics", "💧 IV fluids")
        ),
        DiseaseTreatmentModel(
            "Gallstones",
            listOf("Pain in upper right abdomen", "Nausea", "Vomiting", "Pain after eating"),
            listOf("💊 Pain medication", "🔪 Surgery (if necessary)", "🥗 Low-fat diet", "💧 Hydration")
        ),
        DiseaseTreatmentModel(
            "Gastric Ulcer",
            listOf("Stomach pain", "Burning sensation", "Loss of appetite", "Black stool"),
            listOf("💊 Antacids", "💊 Proton pump inhibitors", "🦠 Antibiotics (if H. pylori)", "🚭 Avoid irritants")
        ),
        DiseaseTreatmentModel(
            "Pancreatitis",
            listOf("Severe upper abdominal pain", "Pain radiating to back", "Nausea", "Vomiting"),
            listOf("🚨 Hospital care", "💧 IV fluids", "💊 Pain management", "🚫 No food temporarily")
        ),
        DiseaseTreatmentModel(
            "Hepatitis",
            listOf("Yellowing of skin/eyes", "Abdominal pain", "Fatigue", "Loss of appetite"),
            listOf("😴 Rest", "💧 Hydration", "🚫 Avoid alcohol", "💊 Antiviral medications (type dependent)")
        ),

        // Pelvis-related Diseases
        DiseaseTreatmentModel(
            "Urinary Tract Infection",
            listOf("Painful urination", "Urinary urgency", "Frequent urination", "Lower back pain"),
            listOf("💊 Antibiotics", "💧 Increased fluid intake", "🍒 Cranberry products", "💊 Pain relievers")
        ),
        DiseaseTreatmentModel(
            "Endometriosis",
            listOf("Pelvic pain", "Menstrual cramps", "Pain during intercourse", "Irregular menstruation"),
            listOf("💊 Pain relievers", "💉 Hormonal therapy", "🔪 Surgery (severe cases)", "🧘 Stress management")
        ),
        DiseaseTreatmentModel(
            "Pelvic Inflammatory Disease",
            listOf("Pelvic pain", "Vaginal discharge", "Painful urination", "Fever"),
            listOf("💊 Antibiotics", "💊 Pain relievers", "😴 Rest", "🚫 Avoid sexual activity until healed")
        ),
        DiseaseTreatmentModel(
            "Prostatitis",
            listOf("Pelvic pain", "Painful urination", "Difficulty starting urination", "Pain with ejaculation"),
            listOf("💊 Antibiotics", "💊 Alpha blockers", "🔥 Warm sitz baths", "💊 Pain relievers")
        ),
        DiseaseTreatmentModel(
            "Interstitial Cystitis",
            listOf("Pelvic pain", "Urinary urgency", "Urinary frequency", "Pain during intercourse"),
            listOf("💊 Pain relievers", "🧪 Bladder instillations", "🥗 Dietary changes", "🧘 Stress management")
        ),
        DiseaseTreatmentModel(
            "Pelvic Floor Dysfunction",
            listOf("Pelvic pain", "Urinary incontinence", "Constipation", "Pain during intercourse"),
            listOf("🏃‍♀️ Pelvic floor physical therapy", "🧘 Biofeedback training", "💊 Pain relievers", "🧘 Relaxation techniques")
        ),

        // Thighs-related Diseases
        DiseaseTreatmentModel(
            "Quadriceps Strain",
            listOf("Thigh pain", "Muscle weakness", "Swelling", "Pain when walking"),
            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory drugs", "🧵 Compression bandage")
        ),
        DiseaseTreatmentModel(
            "Hamstring Strain",
            listOf("Thigh pain", "Pain when walking", "Bruising", "Muscle weakness"),
            listOf("😴 Rest", "🧊 Ice", "💊 Pain relievers", "🏃‍♀️ Rehabilitation exercises")
        ),
        DiseaseTreatmentModel(
            "Meralgia Paresthetica",
            listOf("Numbness", "Tingling", "Burning sensation", "Pain radiating from back"),
            listOf("🥗 Weight loss (if overweight)", "🩳 Loose clothing", "💊 Pain medication", "💉 Corticosteroid injections")
        ),
        DiseaseTreatmentModel(
            "Iliopsoas Tendonitis",
            listOf("Thigh pain", "Pain when climbing stairs", "Pain radiating to knee", "Pain in front of hip"),
            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory medication", "🏃‍♀️ Physical therapy")
        ),
        DiseaseTreatmentModel(
            "Femoral Stress Fracture",
            listOf("Thigh pain", "Pain at rest", "Pain with weight bearing", "Tenderness to touch"),
            listOf("😴 Rest", "🚫 Avoid weight bearing", "💊 Pain medication", "🧵 Crutches or brace")
        ),

        // Calves-related Diseases
        DiseaseTreatmentModel(
            "Deep Vein Thrombosis",
            listOf("Leg swelling", "Pain in the calf", "Warmth in affected area", "Redness"),
            listOf("🩸 Anticoagulants (blood thinners)", "🧦 Compression stockings", "🦵 Elevation of the leg", "🏃‍♀️ Gentle walking")
        ),
        DiseaseTreatmentModel(
            "Gastrocnemius Strain (Calf Strain)",
            listOf("Pain in the calf", "Swelling", "Bruising", "Pain when walking"),
            listOf("😴 Rest", "🧊 Ice", "🧵 Compression bandage", "🦵 Elevation")
        ),
        DiseaseTreatmentModel(
            "Achilles Tendonitis",
            listOf("Pain in the calf", "Pain after exercise", "Stiffness", "Tenderness"),
            listOf("😴 Rest", "🧊 Ice", "💊 Anti-inflammatory medication", "🧵 Supportive shoes")
        ),
        DiseaseTreatmentModel(
            "Compartment Syndrome",
            listOf("Pain in the calf", "Tightness", "Numbness", "Pain with exercise"),
            listOf("😴 Rest", "🧊 Ice", "🦵 Elevation", "🚨 Surgical consultation (severe cases)")
        ),
        DiseaseTreatmentModel(
            "Peripheral Artery Disease",
            listOf("Leg pain", "Pain when walking", "Cold sensation", "Weak pulses"),
            listOf("🚶 Exercise", "🩸 Antiplatelet medications", "🚭 Smoking cessation", "💊 Cholesterol medication")
        ),

        // Feet-related Diseases
        DiseaseTreatmentModel(
            "Plantar Fasciitis",
            listOf("Heel pain", "Foot pain", "Pain when walking", "Pain in morning"),
            listOf("🧵 Supportive shoes", "🏃‍♀️ Stretching exercises", "💊 Anti-inflammatory drugs", "🧊 Ice")
        ),
        DiseaseTreatmentModel(
            "Bunions",
            listOf("Foot pain", "Visible deformity", "Toe pain", "Redness"),
            listOf("🧵 Proper footwear", "🧊 Ice for pain", "🧵 Toe spacers", "🔪 Surgery (advanced cases)")
        ),
        DiseaseTreatmentModel(
            "Morton's Neuroma",
            listOf("Ball of foot pain", "Tingling", "Numbness", "Burning sensation"),
            listOf("🧵 Proper footwear", "🧵 Foot pads", "💉 Corticosteroid injections", "🔪 Surgery (if necessary)")
        ),
        DiseaseTreatmentModel(
            "Diabetic Foot Ulcers",
            listOf("Open sores", "Foot pain", "Skin changes", "Difficulty bearing weight"),
            listOf("🧼 Wound care", "🧵 Off-loading pressure", "💊 Antibiotics (if infected)", "🩺 Regular check-ups")
        ),
        DiseaseTreatmentModel(
            "Gout",
            listOf("Toe pain", "Swelling", "Redness", "Extreme tenderness"),
            listOf("💊 Anti-inflammatory drugs", "💊 Colchicine", "🥗 Diet modifications", "💧 Hydration")
        ),
        DiseaseTreatmentModel(
            "Athlete's Foot",
            listOf("Itching", "Burning sensation", "Skin changes", "Redness"),
            listOf("🧪 Antifungal medication", "🧦 Clean, dry socks", "🧼 Good foot hygiene", "🧵 Breathable shoes")
        )
    )




    fun filterQuestionsByRegions(regionsText: String) {
        val regions = regionsText.split(",").map { it.trim() }

        val filtered = questions.filter { it.bodyRegion in regions }

        _uiState.update { currentState ->
            currentState.copy(
                filteredQuestions = filtered,
                currentQuestionIndex = 0,
                selectedOptionsPerQuestion = emptyMap(),
                isComplete = false,
                error = null,
                dataForResultsPage = null
            )
        }
    }

    fun toggleOption(option: String) {
        val currentQuestionIndex = _uiState.value.currentQuestionIndex
        val currentSelectedOptions = _uiState.value.selectedOptionsPerQuestion[currentQuestionIndex] ?: emptySet()

        val updatedOptions = if (currentSelectedOptions.contains(option)) {
            currentSelectedOptions - option
        } else {
            currentSelectedOptions + option
        }

        _uiState.value = _uiState.value.copy(
            selectedOptionsPerQuestion = _uiState.value.selectedOptionsPerQuestion +
                    (currentQuestionIndex to updatedOptions)
        )
    }

//    fun onNextQuestion() {
//        if (_uiState.value.currentQuestionIndex + 1 < questions.size) {
//            _uiState.value = _uiState.value.copy(
//                currentQuestionIndex = _uiState.value.currentQuestionIndex + 1
//            )
//        } else {
//            _uiState.value = _uiState.value.copy(isComplete = true)
//            analyzeSymptomsAndSaveResult()
//        }
//    }
    fun onNextQuestion() {
        val filtered = _uiState.value.filteredQuestions
        val currentIndex = _uiState.value.currentQuestionIndex

        if (currentIndex + 1 < filtered.size) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = currentIndex + 1
            )
        } else {
            _uiState.value = _uiState.value.copy(isComplete = true)
            analyzeSymptomsAndSaveResult()
        }
    }

    fun onPreviousQuestion() {
        if (_uiState.value.currentQuestionIndex > 0) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = _uiState.value.currentQuestionIndex - 1
            )
        }
    }

    private fun analyzeSymptomsAndSaveResult() {
        viewModelScope.launch {
            try {
                val matchedDiseases = findTopDiseases(_uiState.value.allSelectedSymptoms)
                val result = createDiagnosisResult(matchedDiseases)
                _uiState.value = _uiState.value.copy(
                    dataForResultsPage = result
                )
                // TODO When diagnosis Repository is created
                diagnosisRepository.storeResult(result)
                val allResults = diagnosisRepository.getResults()

                // Get the first diseaseName if exists
                val firstDiseaseName = allResults.firstOrNull()?.diseaseName ?: "No results found"

                // Emit toast message
                Log.d("DiagnosisResult", "First disease: $firstDiseaseName")

                // diagnosisRepository.saveDiagnosisResult(result)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }

        }

    }

    private fun findTopDiseases(symptoms: List<String>): List<DiseaseTreatmentModel> {
        return diseases
            .map { disease ->
                val matchCount = disease.diseaseSymptoms.count { it in symptoms }
                Pair(disease, matchCount)
            }
            .sortedByDescending { it.second }
            .take(2)
            .map { it.first }
    }

    private fun createDiagnosisResult(
        matchedDiseases: List<DiseaseTreatmentModel>
    ): DiagnosisResultItemModel {
        val combinedDiseases = matchedDiseases.joinToString(" or ") { it.diseaseName }
        val combinedTreatments = matchedDiseases
            .flatMap { it.diseaseTreatment }
            .distinct()
            .joinToString("\n\n") { "• $it" }

        return DiagnosisResultItemModel(
            id = System.currentTimeMillis().toInt(),
            date = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date()),
            diseaseName = combinedDiseases,
            diseaseTreatment = combinedTreatments
        )
    }

//    fun getCurrentQuestion(): QuestionAndOptionsModel? {
//        return questions.getOrNull(_uiState.value.currentQuestionIndex)
//    }

    fun getCurrentQuestion(): QuestionAndOptionsModel? {
        return uiState.value.filteredQuestions.getOrNull(uiState.value.currentQuestionIndex)
    }



}