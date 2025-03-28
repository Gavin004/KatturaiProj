package com.example.katturaiproj.u

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.katturaiproj.AppViewModelProvider
import com.example.katturaiproj.KatturaiAppBar
import com.example.katturaiproj.R
import com.example.katturaiproj.model.Favorite.Favorite
import com.example.katturaiproj.model.SingleArticle
import kotlinx.coroutines.launch

//import com.example.katturaiproj.model.Article



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    id:Int,
    navController: NavController,
    modifier: Modifier = Modifier
){

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Log.d("HOME","$id")
    val secondScreenViewModel:SecondScreenViewModel = viewModel()


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { SecondScreenAppBar(
            scrollBehavior = scrollBehavior,
            navController = navController,
            viewModel = secondScreenViewModel
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            LaunchedEffect(Unit) {
                secondScreenViewModel.getSingleArticleVM(id)
            }

            Log.d("ID",id.toString())

            SecondScreenContent(
                singleArticle = secondScreenViewModel.updateStatus.value,
                authorName = secondScreenViewModel.authorNameFun(),
                navController = navController,
                contentPadding = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreenAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    viewModel: SecondScreenViewModel,
    modifier: Modifier = Modifier
){

    val coroutineScope = rememberCoroutineScope()
    val cid = viewModel.check(viewModel.updateStatus.value.article?.id ?: 0).collectAsState(
        initial = Favorite(0,"","", "")
    )
    Log.d("SS-Fav","${cid.value}")
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {},
        navigationIcon ={
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
       modifier = Modifier.shadow(8.dp),
        actions = {
            IconButton(onClick = {

            }){
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Zoom",
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(onClick = {

                coroutineScope.launch {
                    viewModel.toggleFav(
                        id = cid.value?.id,
                        favorite = Favorite(
                            id = viewModel.updateStatus.value.article?.id ?: 0,
                            imgUrl = viewModel.updateStatus.value.article?.thumbnailUrl ?: "",
                            title = viewModel.updateStatus.value.article?.title?:"",
                            desc = viewModel.updateStatus.value.article?.shortDesc ?: ""
                        )
                    )
                }

            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint =if(cid.value!=null) Color.Green else Color.Gray ,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SecondScreenContent(
    singleArticle: SingleArticle,
    authorName : String,
    navController: NavController,
    contentPadding:PaddingValues,
    modifier: Modifier = Modifier
)
{

    val size = singleArticle.article?.content?.size

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = singleArticle.article?.title ?: "" ,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier= Modifier.padding(start = 4.dp, top = 32.dp, bottom = 12.dp)
        )
        Log.d("IMGTH",singleArticle.article?.thumbnailUrl ?: "load")
        AsyncImage(
            model = "https://cdn1.kadalpura.com/articles/ta/${singleArticle.article?.thumbnailUrl}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(216.dp).padding(bottom = 12.dp)
        )
        Row(
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            AsyncImage(
                model = "https://cdn1.kadalpura.com/articles/author_image/${singleArticle.article?.authorId}.png",
                contentDescription = "Author",
                modifier = Modifier.size(48.dp)
//                    .padding(start = 8.dp, end = 16.dp)
            )
            Text(
                text =  authorName,  // todo date near author name
                modifier = Modifier.padding(top = 8.dp)
            )
        }
//        Display()
        Text(
            text = singleArticle.article?.shortDesc ?: "",
            textAlign = TextAlign.Center,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        )

        singleArticle.article?.content?.forEach {
            if(it.para != ""){
                Text(
                    text = it.para ?: "",
                    modifier = Modifier.padding(start = 8.dp, bottom = 12.dp),
                    textAlign = TextAlign.Start
                )
            }
            if (it.imgUrl != ""){
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/${it.imgUrl}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(216.dp).padding(bottom = 12.dp)
                )
            }
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if(size != null) {
                singleArticle.article.content.get(size - 1).tagNames?.forEach {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(
                            text = it,
                            color = Color.White
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        Row {

            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "likes",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(text = singleArticle.article?.likes.toString() ,modifier = Modifier.padding(end = 8.dp))

            Icon(
                painter = painterResource( R.drawable.baseline_thumb_down_24),
                contentDescription = "dislikes",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(text = singleArticle.article?.dislikes.toString(), modifier = Modifier.padding(end = 8.dp))

            Icon(
                painter = painterResource( R.drawable.baseline_menu_book_24),
                contentDescription = "Views",
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(text = singleArticle.article?.viewed.toString(), modifier = Modifier.padding(end = 8.dp))

            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "Share" //todo implementing share option
            )
        }

        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        Row (
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ){
            if(size != null){
                singleArticle.article.content[size - 3].relevance?.forEach{ item ->
                    Column(
                        modifier = Modifier.clickable {
                            navController.navigate("details/${item.id}")
                        }
                    ) {
                        AsyncImage(
                            model = "https://cdn1.kadalpura.com/articles/ta/${item.thumbnailUrl}",
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.width(300.dp).height(200.dp).padding(
                                start = 8.dp, end = 8.dp, bottom = 12.dp
                            )
                        )
                        Text(
                            text = item.title.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier.width(300.dp).padding(start = 8.dp, end = 12.dp)
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))


    }

}

@Composable
fun Display(modifier: Modifier = Modifier){

    Column {
        Text(
            text = "வாங்க அன்பர்களே  இன்று வெற்றிகளை அள்ளப்போகும் ராசிக்கான பலன்களை குறித்து பார்ப்போம்",
            textAlign = TextAlign.Center,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 12.dp, bottom = 24.dp)
        )
        val para1 = listOf(
            "மேஷம் : எந்த காரியத்தை தொட்டாலும் இரண்டு மூன்று முறை முயன்று முடிக்க வேண்டிய சூழ்நிலை உருவாகும். யாருக்கும் பணம், நகை வாங்கித் தருவதில் ஈடுபட வேண்டாம். வியாபாரத்தில் கவனமாகப் பேசுங்கள். உத்தியோகத்தில் கூடுதல் கவனம் செலுத்துவது நல்லது. செலவுகள் அதிகரிக்கும். இதனால் சிலருக்கு சேமிப்பு குறையலாம். மொத்தத்தில், பொறுமை தேவைப்படும் நாள்.",
            "ரிஷபம் : பயணங்களால் அலைச்சல் இருந்தாலும் இறுதியில் ஆதாயமும் உண்டு. எதிர்ப்புகள் அடங்கும். தாய்வழி உறவினர்களால் வீண் செலவுகள் ஏற்படும். வியாபாரத்தில் பங்குதாரர்களை அதிகம் அனுசரித்துச் செல்ல வேண்டி இருக்கும். உத்தியோகத்தில் சக ஊழியர்களால் நிம்மதி கிடைக்கும் .தேவைகள் பூர்த்தியாகும் நாள்.",
            "மிதுனம் : செலவுகள் அடிக்கடி கையிருப்பை கரைக்கும். பிள்ளைகள் வழியில் சின்னச், சின்ன மனக்கவலைகள் வந்து போகும். வேலைச்சுமையால் உடல் அசதி மனச்சோர்வு வந்து நீங்கும். சில விஷயங்களுக்கு அனுபவ அறிவை பயன்படுத்துவது நல்லது. சிலருக்கு லேசாகத் தலை வலிக்கும். வியாபாரத்தில் வாடிக்கையாளர்களிடம் நயமாகப் பேசுங்கள். உத்தியோகத்தில் யாரையும் பகைத்துக் கொள்ளாதீர்கள். பொறுமைத் தேவைப்படும் நாள்.",
            "கடகம் : பல வேலைகளையும் இழுத்துப் போட்டு பார்க்க வேண்டி வரும். அடுத்தவர்களை குறைக் கூறிக் கொண்டிருக்காமல் உங்களை மாற்றிக் கொள்ளப் பாருங்கள். வியாபாரத்தில் வேலையாட்கள் அதிருப்தி அடைவார்கள். உத்தியோகத்தில் வேலைச்சுமை அதிகரிக்கும். சகிப்புத் தன்மை தேவைப்படும் நாள்.",
            "சிம்மம் : செலவுகள் கையிருப்பை கரைக்கும். உடல் ஆரோக்கியத்தில் கவனம் தேவை. எனினும், சவால்கள் விவாதங்களில் போராடி வெற்றி பெறுவீர்கள். உடன்பிறந்தவர்கள் பாசமழை பொழிவார்கள். அரசால் சிலருக்கு ஓரளவு ஆதாயம் உண்டு. அதிகாரப் பதவியில் இருப்பவர்கள் சிலருக்கு உதவுவார்கள். வியாபாரத்தில் அனுபவமிக்க வேலையாட்களை தேடுவீர்கள். உத்தியோகத்தில் பெரிய பொறுப்புகள் தேடி வரும். சுமை கூடினாலும் சாதிக்கும் நாள்.",
        )
        para1.forEach{ item ->
            Text(
                text = item,
                modifier = Modifier.padding(start = 8.dp, bottom = 12.dp),
                textAlign = TextAlign.Start
            )
        }
        AsyncImage(
            model = "https://cdn1.kadalpura.com/articles/ta/images/astrology-08112024-15.png",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(216.dp).padding(bottom = 12.dp)
        )
        val para2 = listOf(
            "கன்னி : குடும்பத்தினருடன் கலந்தாலோசித்து பழைய பிரச்னைகளுக்கு முக்கிய தீர்வு காண்பீர்கள். பிரபலங்களின் நட்பு கிடைக்கும். சொந்த-பந்தங்கள் தேடி வருவார்கள். வியாபாரத்தில் போட்டிகளை எதிர்கொண்டு வெற்றி காண்பீர்கள். உத்தியோகத்தில் உங்கள் கருத்துக்கு ஆதரவு கிடைக்கும். தைரியம் கூடும் நாள்.",
            "துலாம் : உணர்ச்சி வசப்படாமல் அறிவுப்பூர்வமாக முடிவெடுக்கப் பாருங்கள். முன்கோபத்தை குறையுங்கள். வியாபாரத்தில் பற்று வரவு சுமார்தான். உத்தியோகத்தில் மறைமுக நெருக்கடிகளை சமாளிப்பீர்கள். இறுதியில் சோதனைகள் அனைத்தையும் கடந்து சாதனை படைப்பீர்கள். அதனால் கவலை வேண்டாம்.",
            "விருச்சிகம் : தவறு செய்பவர்களை தட்டிக் கேட்பீர்கள். பெற்றோரின் ஒத்துழைப்பு அதிகரிக்கும். விரும்பிய பொருட்களை வாங்கி மகிழ்வீர்கள். காணாமல் போன முக்கிய ஆவணங்கள் கிடைக்கும். வியாபாரத்தில் வாடிக்கையாளர்கள் எண்ணிக்கை அதிகமாகும். உத்தியோகத்தில் உங்கள் கை ஓங்கும். கடமை உணர்வுடன் செயல்படும் நாள்.",
            "தனுசு : கணவன்-மனைவிக்குள் அனுசரித்துப் போவது நல்லது. ஆடம்பரச் செலவுகளால் சேமிப்புகள் கரையும். உறவினர்களால் சங்கடங்கள் வரும். வெளி உணவுகளை தவிர்ப்பது நல்லது. வியாபாரத்தில் வேலையாட்களால் தேவை இல்லாத பிரச்சனைகள் வராமல் பார்த்துக் கொள்ளவும். உத்தியோகத்தில் முக்கிய பொறுப்புகள் அதிகரிக்கும். இதனால் வேலைச்சுமை கூடும். மொத்தத்தில் பல தடைகளைத் தாண்டி முன்னேறும் நாள்.",
            "மகரம் : திடீர் திடீரென்று எதையோ இழந்ததைப் போல் இருப்பீர்கள். மற்றவர்களை முழுமையாக நம்பிக் கொண்டிருக்க வேண்டாம். வியாபாரத்தில் வேலையாட்களிடம் வளைந்து கொடுத்துப் போவது நல்லது. நேர்மறை எண்ணங்கள் தேவைப்படும் நாள்.",
            "கும்பம் : தாய் வழி உறவுகளுடன் சிலருக்கு கருத்துவேறுபாடுகள் வந்து நீங்கும். எனினும், புதிய சிந்தனைகள் மனதில் தோன்றும். பிள்ளைகளால் மகிழ்ச்சியும் உறவினர்களால் ஆதாயமும் உண்டு. நீண்ட நாள் பிரார்த்தனையை நிறைவேற்றுவீர்கள். புதியவர்கள் நண்பர்களாவார்கள். வியாபாரத்தில் வாடிக்கையாளர்களின் எண்ணிக்கை அதிகரிக்கும். எனினும், எதிர்பார்த்த லாபம் கிடைக்க சற்று கூடுதல் முயற்சிகளை மேற்கொள்ள வேண்டி இருக்கும். உத்தியோகத்தில் வேலை பளு காணப்பட்டாலும் கூட இறுதியில், ஒரு புதிய சாதனையைப் படைப்பீர்கள்.",
            "மீனம் : பேச்சில் மட்டும் நிதானமாக இருந்து கொள்ளுங்கள். எனினும், திட்டமிட்ட காரியங்களை சிறப்பாக செய்து முடிப்பீர்கள். தாய்வழி உறவினர்களால் அலைச்சல் ஏற்படும். வெளிவட்டார தொடர்புகள் அதிகரிக்கும். வியாபாரத்தில் லாபம் ஓரளவு உயரும். உத்தியோகத்தில் சக ஊழியர்களால் நிம்மதி கிட்டும். எதிர்பாராத இடத்திலிருந்து உதவிகள் கிட்டும் நாள். "
        )
        para2.forEach{ item ->
            Text(
                text = item,
                modifier = Modifier.padding(start = 8.dp, bottom = 12.dp),
                textAlign = TextAlign.Start
            )
        }
        Row {
            val tags1 = listOf("Zodiac signs","Rasi","Zodiac")
            tags1.forEach{item ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = item,
                        color = Color.White
                    )
                }
            }
        }
        Row {
            val tags2 = listOf("Horoscopes" , "Rasipalan","horoscope")
            tags2.forEach{item ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape = ButtonDefaults.shape,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = item,
                        color = Color.White
                    )
                }
            }
        }

        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        Row {
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(text = "0",modifier = Modifier.padding(end = 8.dp))
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = null
            )
        }
        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )
        val related = listOf(
            Pair("images/astrology-08112024-05.png","05-02-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-04.png","04-02-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-11.png","வார ராசிபலன்! (02.02.2025 - 08.02.2025)"),
            Pair("images/astrology-08112024-03.png","03-02-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-02.png","02-02-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-01.png","01-02-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-11.png","31-01-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-10.png","30-01-2025 - தேதிக்கான ராசி பலன்!"),
            Pair("images/astrology-08112024-09.png","29-01-2025 - தேதிக்கான ராசி பலன்!")
        )
        Text(
            text = "Related Articles:",
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        Row (
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ){
            related.forEach{ item ->
                Column {
                    AsyncImage(
                        model = "https://cdn1.kadalpura.com/articles/ta/${item.first}",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.width(300.dp).height(200.dp).padding(
                            start = 8.dp, end = 8.dp, bottom = 12.dp)
                    )
                    Text(
                        text = item.second,
                        fontSize = 16.sp,
                        modifier = Modifier.width(300.dp).
                            padding(start = 8.dp, end = 12.dp)
                    )
                }
            }
        }
        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
    }
} //