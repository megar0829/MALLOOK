import pickle

category_29cm = [
    {
        "category_name": "여성의류",
        "category_code": 268100100,
        'gender': 'female',
        "category2": [
            {
                "category_name": "상의",
                "category_code": 268103100,
                "category3": [
                    {"category_name": "반소매 티셔츠", "category_code": 268103101, "main_category": "상의", "sub_category": "반팔티"},
                    {"category_name": "긴소매 티셔츠", "category_code": 268103102, "main_category": "상의", "sub_category": "긴팔티"},
                    {"category_name": "슬리브리스", "category_code": 268103103, "main_category": "상의", "sub_category": "민소매"},
                    {"category_name": "스웨트셔츠", "category_code": 268103104, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "후디", "category_code": 268103105, "main_category": "상의", "sub_category": "후드티"},
                    {"category_name": "셔츠", "category_code": 268103106, "main_category": "상의", "sub_category": "셔츠/블라우스"},
                    {"category_name": "블라우스", "category_code": 268103107, "main_category": "상의", "sub_category": "셔츠/블라우스"}
                ]
            },
            {
                "category_name": "바지",
                "category_code": 268106100,
                "category3": [
                    {"category_name": "부츠컷", "category_code": 268106110, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "쇼트", "category_code": 268106108, "main_category": "하의", "sub_category": "숏팬츠"},
                    {"category_name": "슬림", "category_code": 268106103, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "스트레이트", "category_code": 268106101, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "와이드", "category_code": 268106102, "main_category": "하의", "sub_category": "와이드"},
                    {"category_name": "데님", "category_code": 268106104, "main_category": "하의", "sub_category": "데님"},
                    {"category_name": "트레이닝", "category_code": 268106107, "main_category": "하의", "sub_category": "트레이닝/조거팬츠"},
                    {"category_name": "레깅스", "category_code": 268106109, "main_category": "하의", "sub_category": "레깅스"},
                    {"category_name": "슬랙스", "category_code": 268106106, "main_category": "하의", "sub_category": "슬랙스"}
                ],
            },
            {
                "category_name": "원피스",
                "category_code": 268104100,
                "category3": [
                    {"category_name": "미니 원피스", "category_code": 268104101, "main_category": "원피스", "sub_category": "미니원피스"},
                    {"category_name": "미디 원피스", "category_code": 268104102, "main_category": "원피스", "sub_category": "기타"},
                    {"category_name": "롱 원피스", "category_code": 268104103, "main_category": "원피스", "sub_category": "롱원피스"},
                    {"category_name": "데님 원피스", "category_code": 268104104, "main_category": "원피스", "sub_category": "기타"}
                ],
            },
            {
                "category_name": "스커트",
                "category_code": 268107100,
                "category3": [
                    {"category_name": "미니", "category_code": 268107101, "main_category": "하의", "sub_category": "스커트"},
                    {"category_name": "미디", "category_code": 268107102, "main_category": "하의", "sub_category": "스커트"},
                    {"category_name": "롱", "category_code": 268107103, "main_category": "하의", "sub_category": "스커트"},
                    {"category_name": "데님", "category_code": 268107104, "main_category": "하의", "sub_category": "스커트"}
                ],
            },
            {
                "category_name": "아우터",
                "category_code": 268102100,
                "num1": 3,
                "category3": [
                    {"category_name": "무스탕", "category_code": 268102114, "main_category": "아우터", "sub_category": "무스탕"},
                    {"category_name": "바람막이", "category_code": 268102135, "main_category": "아우터", "sub_category": "바람막이"},
                    {"category_name": "레더 재킷", "category_code": 268102106, "main_category": "아우터", "sub_category": "라이더재킷"},
                    {"category_name": "재킷", "category_code": 268102104, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "퍼재킷", "category_code": 268102115, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "나일론 재킷", "category_code": 268102120, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "트레이닝 재킷", "category_code": 268102121, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "데님 재킷", "category_code": 268102107, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "후드 집업", "category_code": 268102110, "main_category": "아우터", "sub_category": "후드집업"},
                    {"category_name": "아노락", "category_code": 268102122, "main_category": "아우터", "sub_category": "바람막이"},
                    {"category_name": "점퍼", "category_code": 268102119, "main_category": "아우터", "sub_category": "점퍼"},
                    {"category_name": "플리스", "category_code": 268102109, "main_category": "아우터", "sub_category": "플리스"},
                    {"category_name": "하프코트", "category_code": 268102124, "main_category": "아우터", "sub_category": "숏코트"},
                    {"category_name": "숏코트", "category_code": 268102123, "main_category": "아우터", "sub_category": "숏코트"},
                    {"category_name": "롱코트", "category_code": 268102125, "main_category": "아우터", "sub_category": "롱코트"},
                    {"category_name": "트렌치 코트", "category_code": 268102101, "main_category": "아우터", "sub_category": "롱코트"},
                    {"category_name": "롱패딩", "category_code": 268102116, "main_category": "아우터", "sub_category": "롱패딩"},
                    {"category_name": "경량패딩", "category_code": 268102118, "main_category": "아우터", "sub_category": "숏패딩/패딩조끼"},
                    {"category_name": "숏패딩", "category_code": 268102117, "main_category": "아우터", "sub_category": "숏패딩/패딩조끼"},
                    {"category_name": "베스트", "category_code": 268102112, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "로브", "category_code": 268102113, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "블루종", "category_code": 268102129, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "야상", "category_code": 268102128, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "바시티", "category_code": 268102127, "main_category": "아우터", "sub_category": "기타"}
                ],
            },
            {
                "category_name": "니트웨어",
                "category_code": 268105100,
                "category3": [
                    {"category_name": "브이넥", "category_code": 268105107, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "크루넥", "category_code": 268105106, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "터틀넥", "category_code": 268105102, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "카디건", "category_code": 268105103, "main_category": "아우터", "sub_category": "가디건"},
                    {"category_name": "베스트", "category_code": 268105104, "main_category": "상의", "sub_category": "니트/스웨터"},
                ],
            }
        ]
    },
    {
        "category_name": "여성가방",
        "category_code": 269100100,
        'gender': 'female',
        "category2": [
            {"category_name": "숄더백", "category_code": 269101100, "category3": [], "main_category": "가방", "sub_category": "숄더백"},
            {"category_name": "크로스백", "category_code": 269102100, "category3": [], "main_category": "가방", "sub_category": "크로스백"},
            {"category_name": "토트백", "category_code": 269103100, "category3": [], "main_category": "가방", "sub_category": "토트백"},
            {"category_name": "에코,캔버스백", "category_code": 269105100, "category3": [], "main_category": "가방", "sub_category": "에코/캔버스백"},
            {"category_name": "백팩", "category_code": 269106100, "category3": [], "main_category": "가방", "sub_category": "백팩"},
            {"category_name": "파우치", "category_code": 269114100, "category3": [], "main_category": "가방", "sub_category": "기타"},
            {"category_name": "클러치", "category_code": 269113100, "category3": [], "main_category": "가방", "sub_category": "클러치"},
        ]
    },
    {
        "category_name": "여성신발",
        "category_code": 270100100,
        "gender": "female",
        "category2": [
            {
                "category_name": "플랫,로퍼",
                "category_code": 270101100,
                "category3": [
                    {"category_name": "플랫", "category_code": 270101101, "main_category": "신발", "sub_category": "플랫슈즈"},
                    {"category_name": "로퍼", "category_code": 270101102, "main_category": "신발", "sub_category": "로퍼"}
                ]
            },
            {
                "category_name": "부츠",
                "category_code": 270103100,
                "category3": [
                    {"category_name": "앵클 부츠", "category_code": 270103101, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "니하이 부츠", "category_code": 270103102, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "플랫 부츠", "category_code": 270103103, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "미드힐 부츠", "category_code": 270103104, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "하이힐 부츠", "category_code": 270103105, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "레인 부츠", "category_code": 270103106, "main_category": "신발", "sub_category": "부츠"}
                ]
            },
            {
                "category_name": "샌들",
                "category_code": 270104100,
                "category3": [
                    {"category_name": "플랫", "category_code": 270104101, "main_category": "신발", "sub_category": "샌들/슬리퍼"},
                    {"category_name": "힐", "category_code": 270104102, "main_category": "신발", "sub_category": "샌들/슬리퍼"}
                ]
            },
            {
                "category_name": "펌프스",
                "category_code": 270102100,
                "category3": [
                    {"category_name": "미드힐", "category_code": 270102101, "main_category": "신발", "sub_category": "힐/펌프스"},
                    {"category_name": "하이힐", "category_code": 270102102, "main_category": "신발", "sub_category": "힐/펌프스"}
                ]
            },
            {
                "category_name": "슬리퍼,뮬",
                "category_code": 270105100,
                "category3": [
                    {"category_name": "슬리퍼", "category_code": 270105101, "main_category": "신발", "sub_category": "샌들/슬리퍼"},
                    {"category_name": "뮬", "category_code": 270105102, "main_category": "신발", "sub_category": "샌들/슬리퍼"},
                    {"category_name": "플립플랍", "category_code": 270105103, "main_category": "신발", "sub_category": "샌들/슬리퍼"}
                ]
            },
            {
                "category_name": "스니커즈",
                "category_code": 270106100,
                "category3": [
                    {"category_name": "하이탑", "category_code": 270106101, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "로우탑", "category_code": 270106102, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "슬립온", "category_code": 270106103, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "러닝화", "category_code": 270106104, "main_category": "신발", "sub_category": "러닝화/워킹화"},
                    {"category_name": "기능성 운동화", "category_code": 270106105, "main_category": "신발", "sub_category": "러닝화/워킹화"}
                ]
            }
        ]
    },
    {
        "category_name": "여성액세서리",
        "category_code": 271100100,
        "category2": [
            {
                "category_name": "모자",
                "category_code": 271102100,
                "category3": [
                    {"category_name": "볼캡", "category_code": 271102103, "main_category": "모자", "sub_category": "볼캡/야구모자"},
                    {"category_name": "버킷햇", "category_code": 271102101, "main_category": "모자", "sub_category": "버킷햇"},
                    {"category_name": "비니", "category_code": 271102104, "main_category": "모자", "sub_category": "비니"},
                    {"category_name": "트루퍼", "category_code": 271102110, "main_category": "모자", "sub_category": "기타"},
                    {"category_name": "베레모", "category_code": 271102102, "main_category": "모자", "sub_category": "베레모"},
                    {"category_name": "페도라", "category_code": 271102105, "main_category": "모자", "sub_category": "페도라"},
                    {"category_name": "바라클라바", "category_code": 271102109, "main_category": "모자", "sub_category": "기타"}
                ]
            }
        ]
    },
    {
        "category_name": "남성의류",
        "category_code": 272100100,
        'gender': 'male',
        "category2": [
            {
                "category_name": "아우터",
                "category_code": 272102100,
                "category3": [
                    {"category_name": "무스탕", "category_code": 272102116, "main_category": "아우터", "sub_category": "무스탕"},
                    {"category_name": "플리스", "category_code": 272102114, "main_category": "아우터", "sub_category": "플리스"},
                    {"category_name": "야상", "category_code": 272102122, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "블루종", "category_code": 272102123, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "바시티", "category_code": 272102124, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "데님 재킷", "category_code": 272102125, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "퍼 재킷", "category_code": 272102126, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "트레이닝 재킷", "category_code": 272102127, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "점퍼", "category_code": 272102128, "main_category": "아우터", "sub_category": "점퍼"},
                    {"category_name": "바람막이", "category_code": 272102129, "main_category": "아우터", "sub_category": "바람막이"},
                    {"category_name": "아노락", "category_code": 272102130, "main_category": "아우터", "sub_category": "바람막이"},
                    {"category_name": "트렌치,맥코트", "category_code": 272102102, "main_category": "아우터", "sub_category": "롱코트"},
                    {"category_name": "나일론,코치 재킷", "category_code": 272102112, "main_category": "아우터", "sub_category": "재킷"},
                    {"category_name": "후드 집업", "category_code": 272102109, "main_category": "아우터", "sub_category": "후드집업"},
                    {"category_name": "베스트", "category_code": 272102106, "main_category": "아우터", "sub_category": "기타"},
                    {"category_name": "레더 재킷", "category_code": 272102111, "main_category": "아우터", "sub_category": "라이더재킷"},
                    {"category_name": "블레이저", "category_code": 272102110, "main_category": "아우터", "sub_category": "블래이저"},
                    {"category_name": "롱코트", "category_code": 272102119, "main_category": "아우터", "sub_category": "롱코트"},
                    {"category_name": "숏코트", "category_code": 272102117, "main_category": "아우터", "sub_category": "숏코트"},
                    {"category_name": "하프코트", "category_code": 272102118, "main_category": "아우터", "sub_category": "숏코트"},
                    {"category_name": "경량패딩", "category_code": 272102105, "main_category": "아우터", "sub_category": "숏패딩/패딩조끼"},
                    {"category_name": "숏패딩", "category_code": 272102104, "main_category": "아우터", "sub_category": "숏패딩/패딩조끼"},
                    {"category_name": "롱패딩", "category_code": 272102103, "main_category": "아우터", "sub_category": "롱패딩"},
                    {"category_name": "기타 아우터", "category_code": 272102113, "main_category": "아우터", "sub_category": "기타"}
                ]
            },
            {
                "category_name": "상의",
                "category_code": 272103100,
                "category3": [
                    {"category_name": "반소매 셔츠", "category_code": 272103105, "main_category": "상의", "sub_category": "셔츠/블라우스"},
                    {"category_name": "반소매 티셔츠", "category_code": 272103101, "main_category": "상의", "sub_category": "반팔티"},
                    {"category_name": "피케,카라 티셔츠", "category_code": 272103104, "main_category": "상의", "sub_category": "기타"},
                    {"category_name": "슬리브리스", "category_code": 272103103, "main_category": "상의", "sub_category": "기타"},
                    {"category_name": "스웨트셔츠", "category_code": 272103107, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "후디", "category_code": 272103108, "main_category": "상의", "sub_category": "후드티"},
                    {"category_name": "집업", "category_code": 272103109, "main_category": "상의", "sub_category": "기타"},
                    {"category_name": "긴소매 티셔츠", "category_code": 272103102, "main_category": "상의", "sub_category": "긴팔티"},
                    {"category_name": "긴소매 셔츠", "category_code": 272103106, "main_category": "상의", "sub_category": "셔츠/블라우스"}
                ]
            },
            {
                "category_name": "하의",
                "category_code": 272104100,
                "category3": [
                    {"category_name": "레깅스", "category_code": 272104110, "main_category": "하의", "sub_category": "레깅스"},
                    {"category_name": "쇼트", "category_code": 272104108, "main_category": "하의", "sub_category": "숏팬츠"},
                    {"category_name": "부츠컷", "category_code": 272104109, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "슬림 팬츠", "category_code": 272104103, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "스트레이트 팬츠", "category_code": 272104101, "main_category": "하의", "sub_category": "기타"},
                    {"category_name": "와이드 팬츠", "category_code": 272104102, "main_category": "하의", "sub_category": "와이드"},
                    {"category_name": "데님 팬츠", "category_code": 272104104, "main_category": "하의", "sub_category": "데님"},
                    {"category_name": "트레이닝 팬츠", "category_code": 272104107, "main_category": "하의", "sub_category": "트레이닝/조거팬츠"},
                    {"category_name": "슬랙스", "category_code": 272104106, "main_category": "하의", "sub_category": "슬랙스"}
                ]
            },
            {
                "category_name": "니트웨어",
                "category_code": 272110100,
                "category3": [
                    {"category_name": "터틀넥", "category_code": 272110103, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "브이넥", "category_code": 272110102, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "크루넥", "category_code": 272110101, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "폴로 셔츠", "category_code": 272110107, "main_category": "상의", "sub_category": "니트/스웨터"},
                    {"category_name": "카디건", "category_code": 272110104, "main_category": "아우터", "sub_category": "가디건"},
                    {"category_name": "베스트", "category_code": 272110105, "main_category": "상의", "sub_category": "기타"},
                    {"category_name": "집업", "category_code": 272110106, "main_category": "아우터", "sub_category": "기타"}
                ]
            }
        ]
    },
    {
        "category_name": "남성가방",
        "category_code": 273100100,
        "gender": "male",
        "category2": [
            {"category_name": "토트백", "category_code": 273103100, "category3": [], "main_category": "가방", "sub_category": "토트백"},
            {"category_name": "웨이스트백", "category_code": 273102100, "category3": [], "main_category": "가방", "sub_category": "기타"},
            {"category_name": "크로스백", "category_code": 273101100, "category3": [], "main_category": "가방", "sub_category": "크로스백"},
            {"category_name": "백팩", "category_code": 273104100, "category3": [], "main_category": "가방", "sub_category": "백팩"},
            {"category_name": "숄더백", "category_code": 273105100, "category3": [], "main_category": "가방", "sub_category": "숄더백"},
            {"category_name": "랩탑백", "category_code": 273106100, "category3": [], "main_category": "가방", "sub_category": "기타"},
            {"category_name": "에코,캔버스백", "category_code": 273107100, "category3": [], "main_category": "가방", "sub_category": "에코/캔버스백"},
            {"category_name": "클러치", "category_code": 273115100, "category3": [], "main_category": "가방", "sub_category": "클러치"},
            {"category_name": "파우치", "category_code": 273116100, "category3": [], "main_category": "가방", "sub_category": "기타"},
            {"category_name": "기타 가방", "category_code": 273110100, "category3": [], "main_category": "가방", "sub_category": "기타"},
        ]
    },
    {
        "category_name": "남성신발",
        "category_code": 274100100,
        "category2": [
            {
                "category_name": "스니커즈",
                "category_code": 274101100,
                "category3": [
                    {"category_name": "하이탑", "category_code": 274101101, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "로우탑", "category_code": 274101102, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "슬립온", "category_code": 274101103, "main_category": "신발", "sub_category": "스니커즈"},
                    {"category_name": "러닝화", "category_code": 274101104, "main_category": "신발", "sub_category": "러닝화/워킹화"},
                    {"category_name": "기능성 운동화", "category_code": 274101105, "main_category": "신발", "sub_category": "러닝화/워킹화"}
                ]
            },
            {
                "category_name": "로퍼",
                "category_code": 274102100,
                "category3": [
                    {"category_name": "페니 로퍼", "category_code": 274102101, "main_category": "신발", "sub_category": "로퍼"},
                    {"category_name": "플레인 로퍼", "category_code": 274102102, "main_category": "신발", "sub_category": "로퍼"},
                    {"category_name": "태슬 로퍼", "category_code": 274102103, "main_category": "신발", "sub_category": "로퍼"}
                ]
            },
            {
                "category_name": "구두",
                "category_code": 274103100,
                "category3": [
                    {"category_name": "더비", "category_code": 274103102, "main_category": "신발", "sub_category": "구두"},
                    {"category_name": "레이스업", "category_code": 274103101, "main_category": "신발", "sub_category": "구두"}
                ]
            },
            {
                "category_name": "부츠",
                "category_code": 274104100,
                "category3": [
                    {"category_name": "첼시 부츠", "category_code": 274104101, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "데저트 부츠", "category_code": 274104102, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "미들 부츠", "category_code": 274104103, "main_category": "신발", "sub_category": "부츠"},
                    {"category_name": "레인 부츠", "category_code": 274104104, "main_category": "신발", "sub_category": "부츠"}
                ]
            },
            {
                "category_name": "샌들",
                "category_code": 274105100,
                "category3": [
                    {"category_name": "슬리퍼", "category_code": 274105101, "main_category": "신발", "sub_category": "샌들/슬리퍼"},
                    {"category_name": "플립플랍", "category_code": 274105102, "main_category": "신발", "sub_category": "샌들/슬리퍼"},
                    {"category_name": "뮬", "category_code": 274105103, "main_category": "신발", "sub_category": "뮬/블로퍼"},
                    {"category_name": "샌들", "category_code": 274105104, "main_category": "신발", "sub_category": "샌들/슬리퍼"}
                ]
            }
        ]
    },
    {
        "category_name": "남성액세서리",
        "category_code": 275100100,
        "category2": [
            {
                "category_name": "모자",
                "category_code": 275101100,
                "category3": [
                    {"category_name": "볼캡", "category_code": 275101103, "main_category": "모자", "sub_category": "볼캡/야구모자"},
                    {"category_name": "버킷햇", "category_code": 275101101, "main_category": "모자", "sub_category": "버킷햇"},
                    {"category_name": "비니", "category_code": 275101104, "main_category": "모자", "sub_category": "비니"},
                    {"category_name": "트루퍼", "category_code": 275101107, "main_category": "모자", "sub_category": "기타"},
                    {"category_name": "베레모", "category_code": 275101102, "main_category": "모자", "sub_category": "베레모"},
                    {"category_name": "페도라", "category_code": 275101105, "main_category": "모자", "sub_category": "페도라"},
                    {"category_name": "바라클라바", "category_code": 275101106, "main_category": "모자", "sub_category": "기타"}
                ]
            }
        ]
    },
]

# 카테고리 번호
category_hiver = {
    '상의': {
        '긴팔티': [
            426, 312, 1427,
        ],
        '반팔티': [
            425, 311, 1426,
        ],
        '민소매': [
            600, 576,
        ],
        '후드티': [
            1424,
        ],
        # 후드티, 후드집업
        '후드티(분류필요)': [
            428, 314,
        ],
        '맨투맨': [
            427, 313, 1423,
        ],
        '니트/스웨터': [
            429, 315, 1425, 1430, 1432, 1433
        ],
        '셔츠/블라우스': [
            432, 433, 434, 435, 436, 601, 437, 800,
            317, 318, 319, 320, 321, 577, 322,
            1445, 1446, 1447,
        ],
        '기타': [
            430, 316, 649,
        ],
    },
    '하의': {
        '데님': [
            441, 325, 1450,
        ],
        '면': [
            440, 324,
        ],  
        '슬랙스': [
            438, 323, 1449,
        ],
        '트레이닝/조거팬츠': [
            443, 327, 656,
        ],
        '숏팬츠': [
            442, 326, 1453,
        ],
        # 스커트, 레깅스, 기타
        '기타(분류필요)': [
            444, 328, 650,
        ], 
        # 데님, 면, 기타
        '스키니(분류필요)': [
            1451,
        ],
        # 데님, 면, 기타
        '와이드(분류필요)': [
            1452,
        ],
    },
    '아우터': {
        # 숏패딩/패딩조끼, 롱패딩
        '패딩(분류필요)': [
            1438, 1439,
        ],
        '숏패딩/패딩조끼': [
            563, 562,
        ],
        '롱패딩': [
            561, 560,
        ],
        # 숏코트, 롱코트
        '코트(분류필요)': [
            418, 306, 1441, 1442, 1443,
        ],
        '라이더재킷': [
            1440,
        ],
        '블레이저': [
            557, 556,
        ],
        '재킷': [
            422, 414, 1435, 1436,
        ],
        # 라이더 재킷, 무스탕, 플리스, 바람막이
        '재킷(분류필요)': [
            420, 308, 1437,
        ],
        # 점퍼, 무스탕, 플리스, 바람막이
        '점퍼(분류필요)': [
            419, 307,
        ],
        '가디건': [
            421, 309, 1432,
        ],
        # 플리스, 후드집업, 바람막이, 기타
        '기타(분류필요)': [
            423, 310, 648,
        ],
        # 재킷, 후드집업, 바람막이, 기타
        '베스트(분류필요)': [
            559, 558,
        ],
    },
    '가방': {
        '크로스백': [
            455, 338,
        ],
        '토트백': [
            1465,
        ],
        '클러치': [
            456, 339, 1466,
        ],
        '에코/캔버스백': [
            454, 337,
        ],
        '백팩': [
            453, 336, 1468,
        ],
        # 웨이스트백, 기타
        '기타(분류필요)': [
            616, 617, 457, 592, 593, 340, 1467, 653, 659,
        ],
    },
    '신발': {
        '스니커즈': [
            447, 330, 1459,
        ],
        # 러닝화/워킹화, 스포츠화(축구, 농구, 테니스)
        '운동화(분류필요)': [
            446, 329, 
        ],
        '스포츠화': [
            672,
        ],
        # 구두, 로퍼
        '구두/로퍼(분류필요)': [
            448, 331,
        ],
        '구두': [
            1462,
        ],
        '로퍼': [
            1460,
        ],
        '부츠': [
            449, 333, 1461,
        ],
        # 샌들/슬리퍼, 뮬/블로퍼
        '샌들/슬리퍼(분류필요)': [
            450, 334, 1463,
        ],
        '기타': [
            451, 335, 651,
        ],
    },
    '모자': {
        # 볼캡/야구모자, 스냅백
        '캡(분류필요)': [
            608, 584,
        ],
        '비니': [
            609, 585,
        ],
        '버킷햇': [
            610, 586,
        ],
        # 스냅백, 베레모, 페도라, 기타
        '기타(분류필요)': [
            611, 587,
        ],
    },
    '빅사이즈': {
        # 긴팔티, 반팔티
        '티셔츠(분류필요)': [
            799,
        ],
        # 후드티, 맨투맨
        '맨투맨/후드(분류필요)': [
            1401,
        ],
        # 니트/스웨터, 가디건
        '니트/가디건(분류필요)': [
            801,
        ],
        # 숏패딩/패딩조끼, 롱패딩, 무스탕, 점퍼, 플리스, 기타
        '점퍼/야상/패딩(분류필요)': [
            803,
        ],
        # 라이더 재킷, 블레이저, 무스탕, 재킷, 플리스, 바람막이
        '자켓/코트(분류필요)': [
            804,
        ],
        # 데님, 면, 슬랙스, 트레이닝/조거팬츠, 스커트, 레깅스, 숏팬츠, 기타
        '팬츠(분류필요)': [
            805,
        ],
    },
    '럭셔리': {
        # 긴팔티, 반팔티
        'PK셔츠(분류필요)': [
            1428,
        ],
    },
    '스포츠': {
        # 바람막이, 아우터[기타]
        '아우터(분류필요)': [
            619,
        ],
        # 긴팔티, 반팔티, 민소매, 후드티, 기타
        '상의(분류필요)': [
            655,
        ],
        # 러닝화/워킹화, 스포츠화
        '신발(분류필요)': [
            658, 1479,
        ],
    },
}

# 29cm 저장 및 호출
with open("TwentyNineCategory.pkl", "wb") as f:
    pickle.dump(category_29cm, f)

with open("TwentyNineCategory.pkl", "rb") as f:
    pickle_29cm = pickle.load(f)

# hiver 저장 및 호출
with open("HiverCategory.pkl", "wb") as f:
    pickle.dump(category_hiver, f)

with open("HiverCategory.pkl", "rb") as f:
    pickle_hiver = pickle.load(f)
    
print(pickle_29cm)
print(pickle_hiver)