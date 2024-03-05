import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/gender.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/onboarding/interests_screen.dart';
import 'package:mallook/feature/sign_up/widgets/form_button.dart';
import 'package:mallook/feature/sign_up/widgets/gender_radio_button.dart';
import 'package:mallook/feature/sign_up/widgets/phone_input_formatter.dart';
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
  String _nickname = "";
  bool _nicknameStatus = false;
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

  bool _isSubmitAvailable() {
    print("IS AVAIALBELE");
    print(_additionalAddress.isEmpty);
    if (!_nicknameStatus) return false;
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

  void setDate(DateTime dateTime) {
    setState(() {
      _year = dateTime.year;
      _month = dateTime.month;
      _day = dateTime.day;
    });
  }

  void _isNicknameValid() {
    setState(() {
      _nicknameStatus = true;
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
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const InterestsScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    double deviceWidth = MediaQuery.of(context).size.width;
    return GestureDetector(
      onTap: _onScaffoldTap,
      child: Scaffold(
        appBar: AppBar(
          surfaceTintColor: Theme.of(context).colorScheme.background,
          backgroundColor: Theme.of(context).colorScheme.background,
          elevation: 3,
          title: Container(
            padding: const EdgeInsets.only(
              top: Sizes.size40,
              bottom: Sizes.size20,
            ),
            alignment: Alignment.center,
            child: Text(
              "회원가입",
              style: TextStyle(
                color: Theme.of(context).colorScheme.primary,
                fontWeight: FontWeight.w700,
                fontSize: Sizes.size28,
              ),
            ),
          ),
        ),
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size20,
              horizontal: Sizes.size48,
            ),
            child: SingleChildScrollView(
              child: Column(
                children: [
                  Gaps.v32,
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "닉네임",
                            style: TextStyle(
                              color: Theme.of(context).colorScheme.primary,
                              fontSize: Sizes.size18,
                              fontWeight: FontWeight.w600,
                            ),
                          ),
                          Text(
                            "입력하지 않으면 자동으로 생성되요",
                            style: TextStyle(
                              color: Theme.of(context).colorScheme.secondary,
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
                              style: TextStyle(
                                color: Theme.of(context).colorScheme.onSurface,
                                fontSize: Sizes.size16,
                              ),
                              decoration: InputDecoration(
                                hintText: "닉네임을 입력해주세요.",
                                focusColor:
                                    Theme.of(context).colorScheme.primary,
                                enabledBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .secondary
                                        .withOpacity(0.5),
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
                                  color:
                                      Theme.of(context).colorScheme.secondary,
                                ),
                                disabledBorder: UnderlineInputBorder(
                                  borderSide: BorderSide(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .secondary
                                        .withOpacity(0.5),
                                  ),
                                ),
                              ),
                            ),
                          ),
                          ElevatedButton(
                            onPressed: _isNicknameValid,
                            style: ElevatedButton.styleFrom(
                              backgroundColor: Theme.of(context)
                                  .colorScheme
                                  .primaryContainer,
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
                              "중복검사",
                              style: TextStyle(
                                color: Theme.of(context).colorScheme.primary,
                                fontWeight: FontWeight.w500,
                                fontSize: Sizes.size16,
                              ),
                            ),
                          )
                        ],
                      ),
                      Gaps.v3,
                      if (_nickname.isNotEmpty && !_nicknameStatus)
                        Text(
                          _nicknameStatus
                              ? "사용 가능한 닉네임입니다."
                              : "사용할 수 없는 닉네임 입니다.",
                          style: TextStyle(
                            fontSize: Sizes.size12,
                            color: _nicknameStatus
                                ? Theme.of(context).colorScheme.tertiary
                                : Theme.of(context).colorScheme.error,
                          ),
                        )
                    ],
                  ),
                  Gaps.v16,
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "생년월일",
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.primary,
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
                                color: Theme.of(context)
                                    .colorScheme
                                    .primaryContainer,
                                border: Border.all(
                                  color: Theme.of(context)
                                      .colorScheme
                                      .primary
                                      .withOpacity(0.7),
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size12,
                                ),
                                boxShadow: [
                                  BoxShadow(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .primary
                                        .withOpacity(0.7),
                                    spreadRadius: 0.1,
                                    blurRadius: 2,
                                    offset: const Offset(1, 3),
                                  )
                                ]),
                            child: Text(
                              '$_year년',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                color: Theme.of(context).colorScheme.primary,
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
                                color: Theme.of(context)
                                    .colorScheme
                                    .primaryContainer,
                                border: Border.all(
                                  color: Theme.of(context)
                                      .colorScheme
                                      .primary
                                      .withOpacity(0.7),
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size12,
                                ),
                                boxShadow: [
                                  BoxShadow(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .primary
                                        .withOpacity(0.7),
                                    spreadRadius: 0.1,
                                    blurRadius: 2,
                                    offset: const Offset(1, 3),
                                  )
                                ]),
                            child: Text(
                              '$_month월',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                color: Theme.of(context).colorScheme.primary,
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
                                color: Theme.of(context)
                                    .colorScheme
                                    .primaryContainer,
                                border: Border.all(
                                  color: Theme.of(context)
                                      .colorScheme
                                      .primary
                                      .withOpacity(0.7),
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size12,
                                ),
                                boxShadow: [
                                  BoxShadow(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .primary
                                        .withOpacity(0.7),
                                    spreadRadius: 0.1,
                                    blurRadius: 2,
                                    offset: const Offset(1, 3),
                                  )
                                ]),
                            child: Text(
                              '$_day일',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                color: Theme.of(context).colorScheme.primary,
                                fontSize: Sizes.size16,
                                fontWeight: FontWeight.w600,
                              ),
                            ),
                          ),
                          GestureDetector(
                            onTap: () => _selectDate(context),
                            child: Container(
                              decoration: BoxDecoration(
                                color: Theme.of(context)
                                    .colorScheme
                                    .primaryContainer,
                                border: Border.all(
                                  color: Theme.of(context)
                                      .colorScheme
                                      .primary
                                      .withOpacity(0.7),
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size12,
                                ),
                                boxShadow: [
                                  BoxShadow(
                                    color: Theme.of(context)
                                        .colorScheme
                                        .primary
                                        .withOpacity(0.7),
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
                                  color: Theme.of(context).colorScheme.primary,
                                  size: Sizes.size18,
                                ),
                              ),
                            ),
                          ),
                        ],
                      )
                    ],
                  ),
                  Gaps.v16,
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        '성별',
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.primary,
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
                  ),
                  Gaps.v16,
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        '전화번호',
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.primary,
                          fontWeight: FontWeight.w600,
                          fontSize: Sizes.size18,
                        ),
                      ),
                      TextField(
                        controller: _phoneController,
                        keyboardType: TextInputType.number,
                        autocorrect: false,
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.onSurface,
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
                          focusColor: Theme.of(context).colorScheme.primary,
                          isDense: false,
                          enabledBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context)
                                  .colorScheme
                                  .secondary
                                  .withOpacity(0.5),
                            ),
                          ),
                          focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context).primaryColor,
                            ),
                          ),
                          hintStyle: TextStyle(
                            fontSize: Sizes.size14,
                            color: Theme.of(context).colorScheme.secondary,
                          ),
                          disabledBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context)
                                  .colorScheme
                                  .secondary
                                  .withOpacity(0.5),
                            ),
                          ),
                        ),
                      ),
                      if (_phone.isNotEmpty &&
                          _phone.length != 4 &&
                          !_phoneStatus)
                        Container(
                          alignment: Alignment.centerRight,
                          child: Text(
                            "올바르지 않은 전화번호 형식 입니다.",
                            style: TextStyle(
                              color: Theme.of(context).colorScheme.error,
                              fontSize: Sizes.size12,
                            ),
                          ),
                        ),
                    ],
                  ),
                  Gaps.v16,
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        '주소',
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.primary,
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
                          style: TextStyle(
                            color: Theme.of(context).colorScheme.onSurface,
                            fontSize: Sizes.size14,
                          ),
                          decoration: InputDecoration(
                            hintText: "주소 검색시 클릭!",
                            focusColor: Theme.of(context).colorScheme.primary,
                            enabledBorder: UnderlineInputBorder(
                              borderSide: BorderSide(
                                color: Theme.of(context)
                                    .colorScheme
                                    .secondary
                                    .withOpacity(0.5),
                              ),
                            ),
                            focusedBorder: UnderlineInputBorder(
                              borderSide: BorderSide(
                                color: Theme.of(context).primaryColor,
                              ),
                            ),
                            hintStyle: TextStyle(
                              fontSize: Sizes.size14,
                              color: Theme.of(context).colorScheme.secondary,
                            ),
                            disabledBorder: UnderlineInputBorder(
                              borderSide: BorderSide(
                                color: Theme.of(context)
                                    .colorScheme
                                    .secondary
                                    .withOpacity(0.5),
                              ),
                            ),
                          ),
                        ),
                      ),
                      TextField(
                        enabled: _additionalAddressInputEnable,
                        controller: _additionalAddressController,
                        style: TextStyle(
                          color: Theme.of(context).colorScheme.onSurface,
                          fontSize: Sizes.size14,
                        ),
                        decoration: InputDecoration(
                          hintText: "나머지 주소 입력",
                          focusColor: Theme.of(context).colorScheme.primary,
                          enabledBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context)
                                  .colorScheme
                                  .secondary
                                  .withOpacity(0.5),
                            ),
                          ),
                          focusedBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context).primaryColor,
                            ),
                          ),
                          hintStyle: TextStyle(
                            fontSize: Sizes.size14,
                            color: Theme.of(context).colorScheme.secondary,
                          ),
                          disabledBorder: UnderlineInputBorder(
                            borderSide: BorderSide(
                              color: Theme.of(context)
                                  .colorScheme
                                  .secondary
                                  .withOpacity(0.5),
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                  Gaps.v16,
                  FormButton(
                    text: !_isSubmitAvailable() ? "입력해주세요" : "다음",
                    disabled: !_isSubmitAvailable(),
                    onTap: _onSubmit,
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
