//data class FlightUiState(
//    val searchQuery: String = "",
//    val airportSuggestions: List<Airport> = emptyList(),
//    val selectedAirport: Airport? = null,
//    val flightResults: List<Flight> = emptyList() // Muncul pas suggestion diklik
//)


//VIEW MODEL
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.*
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class FlightViewModel(private val flightRepository: FlightRepository) : ViewModel() {
//
//    // 1. Ini penampung query ketikan user
//    private val _searchQuery = MutableStateFlow("")
//
//    // 2. Ini penampung airport yang dipilih pas diklik
//    private val _selectedAirport = MutableStateFlow<Airport?>(null)
//
//    // 3. 👑 MESIN SAKTI: Gabungkan semua Flow menjadi satu UI State tunggal!
//    val uiState: StateFlow<FlightUiState> = combine(
//        _searchQuery,
//        _selectedAirport,
//        // Dengerin perubahan searchQuery buat nyari suggestion ke Room
//        _searchQuery.flatMapLatest { query ->
//            if (query.isBlank()) flowOf(emptyList())
//            else flightRepository.getAirportSuggestions(query)
//        },
//        // Dengerin perubahan selectedAirport buat nyari rute penerbangan
//        _selectedAirport.flatMapLatest { airport ->
//            if (airport == null) flowOf(emptyList())
//            else flightRepository.getFlightsFromAirport(airport.id)
//        }
//    ) { query, selected, suggestions, flights ->
//        FlightUiState(
//            searchQuery = query,
//            selectedAirport = selected,
//            airportSuggestions = suggestions,
//            flightResults = flights
//        )
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = FlightUiState()
//    )
//
//    // 4. FUNGSI UNTUK DIFAKSES OLEH UI (Uptade State)
//    fun onSearchQueryChange(newQuery: String) {
//        _searchQuery.value = newQuery
//        _selectedAirport.value = null // Reset hasil penerbangan kalau user ngetik lagi
//    }
//
//    fun onAirportSelected(airport: Airport) {
//        _selectedAirport.value = airport
//        _searchQuery.value = airport.iataCode // Otomatis isi TextField pake kode bandara (misal: SVO)
//    }
//}


// App
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FlightApp(
//    // 1. Taruh ViewModel di sini su (Menggunakan Factory yang kita buat kemarin)
//    viewModel: FlightViewModel = viewModel(factory = FlightViewModel.Factory)
//) {
//    // 2. Collect state dari ViewModel menjadi state Compose
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//    Scaffold(topBar = {
//        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
//    }) { innerPadding ->
//        // 3. OPER SEMUA DATA & FUNGSI KE BAWAH INTERFACE
//        FlightScreen(
//            uiState = uiState,
//            onQueryChange = { viewModel.onSearchQueryChange(it) },
//            onSuggestionClick = { viewModel.onAirportSelected(it) },
//            modifier = Modifier.padding(innerPadding)
//        )
//    }
//}


// Screen
//@Composable
//fun FlightScreen(
//    uiState: FlightUiState,
//    onQueryChange: (String) -> Unit,
//    onSuggestionClick = (Airport) -> Unit,
//modifier: Modifier = Modifier
//){
//    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
//
//        // 1. TEXTFIELD SEARCH (Bisa pakai SearchBar Material 3 biar mirip gambar lo)
//        OutlinedTextField(
//            value = uiState.searchQuery,
//            onValueChange = onQueryChange,
//            label = { Text("Search flight...") },
//            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 2. LOGIKA TAMPILAN (Kondisional berdasarkan State)
//        if (uiState.selectedAirport != null) {
//            // JIKA USER SUDAH KLIK SUGGESTION -> Munculkan Hasil Rute Penerbangan
//            LazyColumn {
//                items(uiState.flightResults) { flight ->
//                    FlightRowItem(flight) // Buat komponen card penerbangan sendiri di sini
//                }
//            }
//        } else {
//            // JIKA USER MASIH NGETIK -> Munculkan Daftar Suggestion (Sesuai Gambar Lo)
//            LazyColumn {
//                items(uiState.airportSuggestions) { airport ->
//                    AirportSuggestionRow(
//                        airport = airport,
//                        modifier = Modifier.clickable { onSuggestionClick(airport) }
//                    )
//                }
//            }
//        }
//    }
//}