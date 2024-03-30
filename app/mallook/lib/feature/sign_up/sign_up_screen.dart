import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/gender.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/onboarding/interests_screen.dart';
import 'package:mallook/feature/sign_up/api/signup_api_service.dart';
import 'package:mallook/feature/sign_up/model/random_nickname_model.dart';
import 'package:mallook/feature/sign_up/widgets/form_button.dart';
import 'package:mallook/feature/sign_up/widgets/gender_radio_button.dart';
import 'package:mallook/feature/sign_up/widgets/phone_input_formatter.dart';
import 'package:mallook/global/mallook_snackbar.dart';
import 'package:remedi_kopo/remedi_kopo.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({super.key});

  @override
  State<SignUpScreen> createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final TextEditingController _nicknameController = TextEditingController();
  final TextEditingController _phoneController = TextEditingController();
  final TextEditingController _addressController = TextEditingController();
  final TextEditingController _additionalAddressController =
      TextEditingController();
  DateTime? _currentBackPressTime;
  String _nickname = "";
  String _phone = "";
  bool _phoneStatus = false;
  late DateTime _birthDate;
  late int _year, _month, _day;
  Gender? _gender;
  KopoModel? kopoModel = null;
  String _zipCode = "";
  String _address = "";
  String _buildingName = "";
  String _additionalAddress = "";
  bool _additionalAddressInputEnable = false;
  late RandomNicknameModel randomNicknameModel;

  bool _isAvailable() {
    if (!_phoneStatus) return false;
    if (_gender == null) return false;
    if (kopoModel == null) return false;
    if (_additionalAddress.isEmpty) return false;
    return true;
  }

  @override
  void initState() {
    super.initState();
    _nicknameController.addListener(() {
      setState(() {
        _nickname = _nicknameController.text;
      });
    });
    _setRandomNickname();

    _phoneController.text = "010-";
    _phoneController.addListener(() {
      setState(() {
        _phone = _phoneController.text;
      });
    });
    _birthDate = DateTime.now();
    setDate(_birthDate);
    _addressController.addListener(() {
      setState(() {
        _address = _addressController.text;
        if (_address.isNotEmpty) {
          _additionalAddressInputEnable = true;
        } else {
          _additionalAddressInputEnable = false;
        }
      });
    });
    _additionalAddressController.addListener(() {
      setState(() {
        _additionalAddress = _additionalAddressController.text;
      });
    });
  }

  @override
  void dispose() {
    _nicknameController.dispose();
    _phoneController.dispose();
    _addressController.dispose();
    _additionalAddressController.dispose();
    super.dispose();
  }

  void _setRandomNickname() async {
    randomNicknameModel = await SignupApiService.getRandomNickname();
    if (randomNicknameModel.nickname != null) {
      _nickname = _nicknameController.text = randomNicknameModel.nickname!;
    }
  }

  void setDate(DateTime dateTime) {
    setState(() {
      _year = dateTime.year;
      _month = dateTime.month;
      _day = dateTime.day;
    });
  }

  void _isPhoneValid() {
    if (_phone.isEmpty) {
      _phoneStatus = false;
      return;
    }

    if (!RegExp(r'^010-?([0-9]{4})-?([0-9]{4})$').hasMatch(_phone)) {
      _phoneStatus = false;
      return;
    }

    _phoneStatus = true;
  }

  void _onScaffoldTap() {
    FocusScope.of(context).unfocus();
  }

  void _setGender(Gender gender) {
    _gender = gender;
    setState(() {});
  }

  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: _birthDate,
      firstDate: DateTime(1950),
      lastDate: DateTime.now(),
      initialDatePickerMode: DatePickerMode.year,
      initialEntryMode: DatePickerEntryMode.calendarOnly,
    );
    if (picked != null && picked != _birthDate) {
      _birthDate = picked;
      setDate(_birthDate);
    }
  }

  void _addressAPI() async {
    KopoModel model = await Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => RemediKopo(),
      ),
    );
    _addressController.text = model.address!;
    kopoModel = model;
    _zipCode = model.zonecode!;
    _address = model.address!;
    _buildingName = model.buildingName!;
  }

  void _onSubmit() {
    if (!_isAvailable()) {
      return;
    }
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const InterestsScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    double deviceWidth = MediaQuery.of(context).size.width;
    return PopScope(
      canPop: false,
      onPopInvoked: (didPop) {
        DateTime now = DateTime.now();
        if (_currentBackPressTime == null ||
            now.difference(_currentBackPressTime!) >
                const Duration(seconds: 2)) {
          _currentBackPressTime = now;
          ScaffoldMessenger.of(context).showSnackBar(
            mallookSnackBar(title: '한번 더 누르면 앱이 종료됩니다.'),
          );
          return;
        }
        SystemNavigator.pop();
      },
      child: GestureDetector(
        onTap: _onScaffoldTap,
        child: Scaffold(
          backgroundColor: Colors.white,
          body: SafeArea(
            child: Padding(
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size20,
                horizontal: Sizes.size48,
              ),
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    Container(
                      padding: const EdgeInsets.only(
                        top: Sizes.size40,
                        bottom: Sizes.size32,
                      ),
                      alignment: Alignment.center,
                      child: Text(
                        "회원가입",
                        style: TextStyle(
                          color: Theme.of(context).primaryColor,
                          fontWeight: FontWeight.w700,
                          fontSize: Sizes.size36,
                        ),
                      ),
                    ),
                    Gaps.v32,
                    nicknameBox(context),
                    Gaps.v16,
                    birthdayBox(context, deviceWidth),
                    Gaps.v16,
                    genderBox(context),
                    Gaps.v16,
                    phoneBox(context),
                    Gaps.v16,
                    addressBox(context),
                  ],
                ),
              ),
            ),
          ),
          bottomNavigationBar: BottomAppBar(
            height: Sizes.size96 + Sizes.size18,
            color: Colors.white,
            shadowColor: Colors.white,
            surfaceTintColor: Colors.white,
            elevation: 5,
            child: Padding(
              padding: const EdgeInsets.only(
                top: Sizes.size6,
                left: Sizes.size24,
                right: Sizes.size24,
                bottom: Sizes.size20,
              ),
              child: Center(
                child: FormButton(
                  text: !_isAvailable() ? "입력해주세요" : "다음",
                  disabled: !_isAvailable(),
                  onTap: _onSubmit,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }

  Column addressBox(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          '주소',
          style: TextStyle(
            color: Theme.of(context).primaryColor,
            fontWeight: FontWeight.w600,
            fontSize: Sizes.size18,
          ),
        ),
        GestureDetector(
          onTap: () => _addressAPI(),
          child: TextFormField(
            enabled: false,
            controller: _addressController,
            autocorrect: false,
            style: const TextStyle(
              color: Colors.black,
              fontSize: Sizes.size14,
            ),
            decoration: InputDecoration(
              hintText: "주소 검색시 클릭!",
              focusColor: Theme.of(context).primaryColor,
              enabledBorder: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Colors.grey.shade400,
                ),
              ),
              focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Theme.of(context).primaryColor,
                ),
              ),
              hintStyle: TextStyle(
                fontSize: Sizes.size14,
                color: Colors.grey.shade500,
              ),
            ),
          ),
        ),
        AnimatedOpacity(
          opacity: _additionalAddressInputEnable ? 1 : 0,
          duration: const Duration(milliseconds: 300),
          child: TextField(
            enabled: _additionalAddressInputEnable,
            controller: _additionalAddressController,
            style: const TextStyle(
              color: Colors.black,
              fontSize: Sizes.size14,
            ),
            decoration: InputDecoration(
              hintText: "나머지 주소 입력",
              focusColor: Theme.of(context).primaryColor,
              enabledBorder: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Colors.grey.shade400,
                ),
              ),
              focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(
                  color: Theme.of(context).primaryColor,
                ),
              ),
              hintStyle: TextStyle(
                fontSize: Sizes.size14,
                color: Colors.grey.shade500,
              ),
            ),
          ),
        )
      ],
    );
  }

  Column phoneBox(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          '전화번호',
          style: TextStyle(
            color: Theme.of(context).primaryColor,
            fontWeight: FontWeight.w600,
            fontSize: Sizes.size18,
          ),
        ),
        TextField(
          controller: _phoneController,
          keyboardType: TextInputType.number,
          autocorrect: false,
          style: const TextStyle(
            color: Colors.black,
            fontSize: Sizes.size16,
          ),
          onChanged: (value) => _isPhoneValid(),
          inputFormatters: <TextInputFormatter>[
            FilteringTextInputFormatter.digitsOnly,
            LengthLimitingTextInputFormatter(11),
            PhoneInputFormatter(),
          ],
          decoration: InputDecoration(
            hintText: "전화번호를 입력해주세요.",
            focusColor: Theme.of(context).primaryColor,
            isDense: false,
            enabledBorder: UnderlineInputBorder(
              borderSide: BorderSide(
                color: Colors.grey.shade400,
              ),
            ),
            focusedBorder: UnderlineInputBorder(
              borderSide: BorderSide(
                color: Theme.of(context).primaryColor,
              ),
            ),
            hintStyle: TextStyle(
              fontSize: Sizes.size14,
              color: Colors.grey.shade500,
            ),
          ),
        ),
        if (_phone.isNotEmpty && _phone.length == 4)
          Container(
            alignment: Alignment.centerRight,
            child: Text(
              "'-' 없이 전화번호만 입력해 주세요",
              style: TextStyle(
                fontSize: Sizes.size12,
                color: Colors.grey.shade700,
              ),
            ),
          ),
        if (_phone.isNotEmpty && _phone.length != 4 && !_phoneStatus)
          Container(
            alignment: Alignment.centerRight,
            child: const Text(
              "올바르지 않은 전화번호 형식 입니다.",
              style: TextStyle(
                fontSize: Sizes.size12,
                color: Colors.red,
              ),
            ),
          ),
      ],
    );
  }

  Column genderBox(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          '성별',
          style: TextStyle(
            color: Theme.of(context).primaryColor,
            fontWeight: FontWeight.w600,
            fontSize: Sizes.size18,
          ),
        ),
        Gaps.v10,
        Center(
          child: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              GenderRadioButton(
                text: '남자',
                isSelected: Gender.man == _gender,
                onTap: () => _setGender(Gender.man),
              ),
              Gaps.h12,
              GenderRadioButton(
                text: '여자',
                isSelected: Gender.woman == _gender,
                onTap: () => _setGender(Gender.woman),
              )
            ],
          ),
        )
      ],
    );
  }

  Column birthdayBox(BuildContext context, double deviceWidth) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          "생년월일",
          style: TextStyle(
            color: Theme.of(context).primaryColor,
            fontWeight: FontWeight.w600,
            fontSize: Sizes.size18,
          ),
        ),
        Gaps.v8,
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Container(
              width: deviceWidth / 4,
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size10,
              ),
              decoration: BoxDecoration(
                  color: Theme.of(context).primaryColorLight,
                  border: Border.all(
                    color: Theme.of(context).primaryColor,
                  ),
                  borderRadius: BorderRadius.circular(
                    Sizes.size12,
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Theme.of(context).primaryColorLight,
                      spreadRadius: 0.1,
                      blurRadius: 2,
                      offset: const Offset(1, 3),
                    )
                  ]),
              child: Text(
                '$_year년',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Theme.of(context).primaryColor,
                  fontSize: Sizes.size16,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ),
            Container(
              width: deviceWidth / 7,
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size10,
              ),
              decoration: BoxDecoration(
                  color: Theme.of(context).primaryColorLight,
                  border: Border.all(
                    color: Theme.of(context).primaryColor,
                  ),
                  borderRadius: BorderRadius.circular(
                    Sizes.size12,
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Theme.of(context).primaryColorLight,
                      spreadRadius: 0.1,
                      blurRadius: 2,
                      offset: const Offset(1, 3),
                    )
                  ]),
              child: Text(
                '$_month월',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Theme.of(context).primaryColor,
                  fontSize: Sizes.size16,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ),
            Container(
              width: deviceWidth / 7,
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size10,
              ),
              decoration: BoxDecoration(
                  color: Theme.of(context).primaryColorLight,
                  border: Border.all(
                    color: Theme.of(context).primaryColor,
                  ),
                  borderRadius: BorderRadius.circular(
                    Sizes.size12,
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Theme.of(context).primaryColorLight,
                      spreadRadius: 0.1,
                      blurRadius: 2,
                      offset: const Offset(1, 3),
                    )
                  ]),
              child: Text(
                '$_day일',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Theme.of(context).primaryColor,
                  fontSize: Sizes.size16,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ),
            GestureDetector(
              onTap: () => _selectDate(context),
              child: Container(
                decoration: BoxDecoration(
                  color: Theme.of(context).primaryColorLight,
                  border: Border.all(
                    color: Theme.of(context).primaryColor,
                  ),
                  borderRadius: BorderRadius.circular(
                    Sizes.size12,
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Theme.of(context).primaryColorLight,
                      spreadRadius: 0.1,
                      blurRadius: 2,
                      offset: const Offset(1, 3),
                    )
                  ],
                ),
                child: Padding(
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size10,
                    horizontal: Sizes.size12,
                  ),
                  child: FaIcon(
                    FontAwesomeIcons.calendar,
                    color: Theme.of(context).primaryColor,
                    size: Sizes.size18,
                  ),
                ),
              ),
            ),
          ],
        )
      ],
    );
  }

  Column nicknameBox(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              "닉네임",
              style: TextStyle(
                color: Theme.of(context).primaryColor,
                fontSize: Sizes.size18,
                fontWeight: FontWeight.w600,
              ),
            ),
            Text(
              "닉네임 추천 받을까요?",
              style: TextStyle(
                color: Colors.grey.shade600,
                fontSize: Sizes.size14,
              ),
            )
          ],
        ),
        Row(
          children: [
            Expanded(
              child: TextField(
                controller: _nicknameController,
                keyboardType: TextInputType.text,
                autocorrect: false,
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: Sizes.size16,
                ),
                decoration: InputDecoration(
                  hintText: "닉네임을 입력해주세요.",
                  focusColor: Colors.grey.shade500,
                  enabledBorder: UnderlineInputBorder(
                    borderSide: BorderSide(
                      color: Colors.grey.shade400,
                    ),
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(
                      color: Theme.of(context)
                          .primaryColor, // 포커스가 될 때 보라색으로 변경될 테두리 색상
                    ),
                  ),
                  hintStyle: TextStyle(
                    fontSize: Sizes.size14,
                    color: Colors.grey.shade500,
                  ),
                ),
              ),
            ),
            ElevatedButton(
              onPressed: _setRandomNickname,
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).primaryColorLight,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(
                    Sizes.size14,
                  ),
                ),
                padding: const EdgeInsets.symmetric(
                  horizontal: Sizes.size14,
                ),
              ),
              child: Text(
                "재생성",
                style: TextStyle(
                  color: Theme.of(context).primaryColorDark,
                  fontWeight: FontWeight.w500,
                  fontSize: Sizes.size16,
                ),
              ),
            )
          ],
        ),
      ],
    );
  }
}
